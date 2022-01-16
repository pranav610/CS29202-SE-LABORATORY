#include <bits/stdc++.h>
#include <nodes.hpp>
#include <ways.hpp>
#include <case1.hpp>
#include <case2.hpp>

using namespace std;

int main()
{
    unordered_map<long long, nodes> nodesList;
    vector<ways> waysList;
    cout << "map.osm file readed successfully!!!\n";
    storeInput(nodesList, waysList);
    int choice;
    cout << "Choice-1: Search a Node\n";
    cout << "Choice-2: Find first K nearest Nodes\n";
    cout << "Choice-3: Find shortest distance between two Nodes\n";
    cout << "Choice-4: Exit the Program\n";
    cout << "Enter the Choice Number: ";
    cin >> choice;
    getchar();
    switch (choice)
    {
    case 1:
    {
        cout << "Search the Node with name: ";
        string nameSearch;
        cin >> nameSearch;
        searchInput(nodesList, nameSearch);
        break;
    }
    case 2:
    {   
        long long int id1, id2;
        cout << "Enter ID of 1st Node: ";
        cin >> id1;
        cout << "Enter ID of 2nd Node: ";
        cin >> id2;

        break;
    }
    case 3:
    {
        break;
    }
    default:
        break;
    }

        

}