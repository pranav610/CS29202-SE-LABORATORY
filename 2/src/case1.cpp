#include <bits/stdc++.h>
#include <nodes.hpp>
#include <ways.hpp>
#include <case1.hpp>
#include <rapidxml.hpp>
#include <rapidxml_utils.hpp>

using namespace rapidxml;
using namespace std;

void storeInput(unordered_map<long long int, nodes> &nodesList, vector<ways> &waysList)
{
    file<> file("map.osm");
    xml_document<> doc;
    doc.parse<0>(file.data());
    xml_node<> *node = doc.first_node()->first_node();
    int countNode = 0, countWay = 0;
    for (xml_node<> *node = doc.first_node()->first_node(); node; node = node->next_sibling())
    {
        if (!strcmp(node->name(), "node"))
        {
            nodes temp;
            for (xml_attribute<> *attr = node->first_attribute(); attr; attr = attr->next_attribute())
            {
                if (!strcmp(attr->name(), "id"))
                {
                    temp.id = stoll(attr->value());
                }
                if (!strcmp(attr->name(), "lat"))
                {
                    temp.lat = stold(attr->value());
                }
                if (!strcmp(attr->name(), "lon"))
                {
                    temp.lon = stold(attr->value());
                }
            }
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
            nodesList[temp.id] = temp;
            countNode++;
        }
        if (!strcmp(node->name(), "way"))
        {
            ways temp;
            for (xml_attribute<> *attr = node->first_attribute(); attr; attr = attr->next_attribute())
            {
                if (!strcmp(attr->name(), "id"))
                {
                    temp.id = stoll(attr->value());
                }
            }
            nodes tempNode;
            for (xml_node<> *elementNode = node->first_node(); elementNode; elementNode = elementNode = elementNode->next_sibling())
            {
                if (!strcmp(elementNode->name(), "nd"))
                {
                    temp.wayPoints.push_back(nodesList[stod(elementNode->first_attribute()->value())]);
                }
            }
            waysList.push_back(temp);
            countWay++;
        }
    }
    cout << "Number of Nodes are: " << countNode << "\n";
    cout << "Number of Ways are: " << countWay << "\n";
}

void printNode(nodes input)
{
    cout << "ID of the node is: " << input.id << endl;
    cout << "Name of the node is: " << input.name << endl;
    cout << "Latitude: " << input.lat << " Longitude: " << input.lon << endl;
}

void searchInput(unordered_map<long long int, nodes> &nodesList, string input)
{
    int count = 1;
    unordered_map<long long int, nodes>::iterator itr;
    cout << "Found following nodes: \n";
    for (itr = nodesList.begin(); itr != nodesList.end(); itr++)
    {
        if (itr->second.name.find(input) != -1)
        {
            cout << count++ << ".\n";
            printNode(itr->second);
        }
    }
    cout << endl;
}