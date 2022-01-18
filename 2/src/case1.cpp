#include <bits/stdc++.h>
#include <nodes.hpp>
#include <ways.hpp>
#include <case1.hpp>
#include <rapidxml.hpp>
#include <rapidxml_utils.hpp>

using namespace rapidxml;
using namespace std;

// will store input into nodesList and waysList
void storeInput(unordered_map<ll, nodes> &nodesList, vector<ways> &waysList)
{
    file<> file("map.osm"); // to store the file name
    xml_document<> doc;
    doc.parse<0>(file.data()); // parsing map.osm file, this function will store all elements in doc in tree data structure

    xml_node<> *node = doc.first_node()->first_node(); // first node is osm and its first child is bounds. Nodes and ways are siblings of bounds
    int countNode = 0, countWay = 0;
    for (xml_node<> *node = doc.first_node()->first_node(); node; node = node->next_sibling()) // iteration of all siblings of the element bounds
    {
        if (!strcmp(node->name(), "node")) // element with name node
        {   
            // storing attributes of node
            nodes temp;
            for (xml_attribute<> *attr = node->first_attribute(); attr; attr = attr->next_attribute())
            {
                if (!strcmp(attr->name(), "id"))
                {
                    temp.id = stoll(attr->value()); // stoll will convert string into long long
                }
                if (!strcmp(attr->name(), "lat"))
                {
                    temp.lat = stold(attr->value()); // stold will convert string to long double
                }
                if (!strcmp(attr->name(), "lon"))
                {
                    temp.lon = stold(attr->value()); // stold will convert string to long double
                }
            }
            // searching for child element with attribute value name
            for (xml_node<> *elementNode = node->first_node(); elementNode; elementNode = elementNode->next_sibling())
            {
                for (xml_attribute<> *attr = elementNode->first_attribute(); attr; attr = attr->next_attribute())
                {
                    if (!strcmp(attr->value(), "name"))
                    {
                        temp.name = attr->next_attribute()->value();
                        break;
                    }
                }
            }
            nodesList[temp.id] = temp; // adding data to nodes list
            countNode++; // increasing count
        }
        if (!strcmp(node->name(), "way"))
        {   
            // storing attributes of element with name way
            ways temp;
            for (xml_attribute<> *attr = node->first_attribute(); attr; attr = attr->next_attribute())
            {
                if (!strcmp(attr->name(), "id"))
                {
                    temp.id = stoll(attr->value());
                }
            }
            nodes tempNode;
            // iterating through all child elements of the element way to store all way points
            for (xml_node<> *elementNode = node->first_node(); elementNode; elementNode = elementNode = elementNode->next_sibling())
            {
                if (!strcmp(elementNode->name(), "nd"))
                {
                    temp.wayPoints.push_back(nodesList[stod(elementNode->first_attribute()->value())]);
                }
            }
            waysList.push_back(temp);
            countWay++; // increasing count
        }
    }
    cout << "\nNumber of Nodes are: " << countNode << "\n";
    cout << "Number of Ways are: " << countWay << "\n";
}

// this function will print all details related to the node input 
void printNode(nodes input)
{
    cout << "ID of the node is: " << input.id << endl;
    cout << "Name of the node is: " << input.name << endl;
    cout << "Latitude: " << input.lat << " Longitude: " << input.lon << endl;
}

// search using matching substrings
void searchInput(unordered_map<ll, nodes> &nodesList, string input)
{
    int count = 1;
    unordered_map<ll, nodes>::iterator itr;
    cout << "\nFound following nodes: \n";
    for (itr = nodesList.begin(); itr != nodesList.end(); itr++)
    {
        if (itr->second.name.find(input) != -1) // string::find() function will return starting index of input substring in given string, -1 is not found
        {
            cout << count++ << ".\n";
            printNode(itr->second);
        }
    }
    cout << endl;
}