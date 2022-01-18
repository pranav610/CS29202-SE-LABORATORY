#include <bits/stdc++.h>
#include <nodes.hpp>
#include <ways.hpp>
#include <case1.hpp>
#include <case2.hpp>
#include <case3.hpp>

using namespace std;

int main()
{
    unordered_map<long long int, nodes> nodesList;
    unordered_map<long long int, unordered_map<long long int, long double>> adjList;
    vector<ways> waysList;
    cout << "map.osm file readed successfully!!!\n";
    storeInput(nodesList, waysList);
    createAdjList(adjList, nodesList, waysList);

    int choice;
    do
    {
        cout << "\nChoice-1: Search a Node\n";
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
            long long int id;
            int k;
            cout << "Enter ID of the Node: ";
            cin >> id;
            cout << "Enter value of K: ";
            cin >> k;
            if (nodesList.find(id) == nodesList.end())
            {
                cout << "Invalid Node ID\n";
                break;
            }
            findKNearest(nodesList, id, k);
            break;
        }
        case 3:
        {
            long long int sourceID, destID;
            cout << "Enter Source ID: ";
            cin >> sourceID;
            cout << "Enter Destination ID: ";
            cin >> destID;
            if (nodesList.find(sourceID) == nodesList.end())
            {
                cout << "Invalid Source ID\n";
                break;
            }
            if (nodesList.find(destID) == nodesList.end())
            {
                cout << "Invalid Destination ID\n";
                break;
            }
            dijkstra(adjList, nodesList, nodesList[sourceID], nodesList[destID]);
            break;
        }
        case 4:
        {
            cout << "\nThank You!!!\n\n";
            break;
        }
        default:
            cout << "Enter a Correct Choice Number\n";
            break;
        }
    } while (choice != 4);
}