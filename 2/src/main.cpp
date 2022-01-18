#include <bits/stdc++.h>
#include <nodes.hpp>
#include <ways.hpp>
#include <case1.hpp>
#include <case2.hpp>
#include <case3.hpp>

using namespace std;

int main()
{
    unordered_map<ll, nodes> nodesList; // node list
    unordered_map<ll, unordered_map<ll, ld>> adjList; // adjacency list for the graph
    vector<ways> waysList; // list of all ways
    storeInput(nodesList, waysList); // read and store osm file
    cout << "map.osm file readed successfully!!!\n";
    createAdjList(adjList, nodesList, waysList); // adjancency list creation

    int choice; 
    do
    {   // printing list of choices
        cout << "\nChoice-1: Search a Node\n";
        cout << "Choice-2: Find First K Nearest Nodes\n";
        cout << "Choice-3: Find Shortest Distance between Two Nodes\n";
        cout << "Choice-4: Exit the Program\n";
        cout << "Enter the Choice Number: ";
        cin >> choice;
        switch (choice)
        {
        case 1: // part-1 searching nodes containing given substring
        {
            cout << "Search the Node with name: ";
            getchar();
            string nameSearch;
            cin >> nameSearch;
            searchInput(nodesList, nameSearch); // implemented in case1.cpp 
            break;
        }
        case 2: // finding k nearest nodes
        {
            ll id;
            int k;
            cout << "Enter ID of the Node: "; // input node with id
            cin >> id;
            cout << "Enter value of K: ";
            cin >> k;
            if (nodesList.find(id) == nodesList.end()) // checking for valid input
            {
                cout << "Invalid Node ID\n";
                break;
            }
            findKNearest(nodesList, id, k); // implemented in case2.cpp
            break;
        }
        case 3: // finding path between two nodes
        {
            ll sourceID, destID; // input with id
            cout << "Enter Source ID: ";
            cin >> sourceID;
            cout << "Enter Destination ID: ";
            cin >> destID;
            if (nodesList.find(sourceID) == nodesList.end()) // checking for valid input
            {
                cout << "Invalid Source ID\n";
                break;
            }
            if (nodesList.find(destID) == nodesList.end()) // checking for valid input
            {
                cout << "Invalid Destination ID\n";
                break;
            }
            dijkstra(adjList, nodesList, nodesList[sourceID], nodesList[destID]); // implemented in case3.cpp
            break;
        }
        case 4: // exit condition
        {
            cout << "\nThank You!!!\n\n";
            break;
        }
        default: // to handle wrong choice
            cout << "Enter a Correct Choice Number\n";
            break;
        }
    } while (choice != 4);
}