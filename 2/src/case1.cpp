#include <bits/stdc++.h>
#include <nodes.hpp>
#include <ways.hpp>
#include <rapidxml.hpp>
#include <rapidxml_utils.hpp>

using namespace rapidxml;
using namespace std;

void storeInput(vector<nodes> nodesList)
{   
    file<> file("map.osm");
    xml_document<> doc;
    doc.parse<0>(file.data());
    xml_node<> *node = doc.first_node()->first_node();
    int countNode = 0, countWay = 0;
    for(xml_node<> *node = doc.first_node()->first_node(); node; node = node->next_sibling()){
        if (!strcmp(node->name(), "node"))
        {       
            for(xml_attribute<> *attr = node->first_attribute(); attr; attr = attr->next_attribute()){
                if(strcmp(attr->name(),"id")){

                }
            }
            
            // xml_node<> *elementNode = node->first_node();
            // do
            // {   

            //     elementNode = elementNode->next_sibling();
            // }
            // while (elementNode != node->last_node());
                countNode++;
        }
        if (!strcmp(node->name(), "way"))
        {
            countWay++;
        }
    }
    cout << "Number of Nodes are: " << countNode << "\n";
    cout << "Number of Ways are: " << countWay << "\n";
}