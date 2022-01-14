#include <iostream>
#include "rapidxml.hpp"
#include "rapidxml_utils.hpp"
#include <string.h>

using namespace rapidxml;
using namespace std;

int main(){
    file<> file("map.osm");
    xml_document<> doc;
    doc.parse<0>(file.data());
    char nameNode [] = "node";
    char nameWay [] = "way";
    // xml_node<> *rootNode = doc.first_node("osm");
    xml_node<> *node = doc.first_node()->first_node();
    int countNode = 0, countWay = 0;
    while(node!=doc.first_node()->last_node()){
        if(!strcmp(node->name(),nameNode)){
            countNode++;
        }
        if(!strcmp(node->name(),nameWay)){
            countWay++;
        }
        node = node->next_sibling();
    }
    cout << "Number of Nodes are: " << countNode << "\n";
    cout << "Number of Ways are: " << countWay << "\n";
}