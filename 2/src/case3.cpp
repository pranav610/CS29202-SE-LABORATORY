#include <bits/stdc++.h>
#include <nodes.hpp>
#include <ways.hpp>
#include <case1.hpp>
#include <case2.hpp>
#include <case3.hpp>

const ld INF = 1e9 + 10;

using namespace std;

// path printing using parent matrix O(number of nodes between source and destination in shortest path)
void printPath(unordered_map<ll, ll> &parent, unordered_map<ll, nodes> &nodesList, ll dest, ll source)
{
    vector<nodes> path; // to store nodes in the path
    cout << "Following is Path for the given source and destination: \n";
    path.insert(path.begin(), nodesList[dest]);
    nodes temp = nodesList[parent[dest]];
    while (temp.id != source)
    {
        path.insert(path.begin(), temp);
        temp = nodesList[parent[temp.id]];
    }
    path.insert(path.begin(), nodesList[source]);
    cout << "Path contains " << path.size() << " nodes. Do you want to print all of them ?\nEnter 1 to print path, 0 to skip path printing\n";
    int choice;
    cout << "Choice: ";
    cin >> choice;
    cout << endl;
    if (choice)
    {
        for (int i = 0; i < path.size(); i++)
        {
            cout << i + 1 << ". " << endl;
            printNode(path[i]);
        }
    }
}

// creation of adjacency list 
void createAdjList(unordered_map<ll, unordered_map<ll, ld>> &adjList, unordered_map<ll, nodes> &nodesList, vector<ways> waysList)
{
    for (int i = 0; i < waysList.size(); i++) // TC = O(number of ways)
    {
        for (int j = 0; j < waysList[i].wayPoints.size() - 1; j++) // TC = O(number of nodes in the way)
        {
            ld wt = crowFlyDist(waysList[i].wayPoints[j].lat, waysList[i].wayPoints[j].lon, waysList[i].wayPoints[j + 1].lat, waysList[i].wayPoints[j + 1].lon);
            adjList[waysList[i].wayPoints[j].id][waysList[i].wayPoints[j + 1].id] = wt;
            adjList[waysList[i].wayPoints[j + 1].id][waysList[i].wayPoints[j].id] = wt;
        }
    }
}

// dijkstra implementation with priority queue TC = O(|E|log|V|) 
void dijkstra(unordered_map<ll, unordered_map<ll, ld>> &adjList, unordered_map<ll, nodes> &nodesList, nodes source, nodes dest)
{
    priority_queue<Pair, vector<Pair>, compareDist> pq; // min heap
    unordered_map<ll, ld> dist; // array to store distance from source node to other nodes
    unordered_map<ll, bool> isVisited; // checking if some node is already visited or not 
    unordered_map<ll, ll> parent; // for storing parent of each visited node

    unordered_map<ll, nodes>::iterator itr;
    for (itr = nodesList.begin(); itr != nodesList.end(); itr++) // initialising above data structures TC = O(|V|)
    {
        isVisited[itr->first] = false;
        dist[itr->first] = INF;
        parent[itr->first] = -1;
    }

    pq.push(make_pair(0, source)); // adding source in priority queue with 0 distance
    dist[source.id] = 0;

    while (!pq.empty()) // while loop until priority queue is not empty
    {
        nodes temp = pq.top().second;
        isVisited[temp.id] = true;
        pq.pop();

        unordered_map<ll, ld>::iterator itr;
        for (itr = adjList[temp.id].begin(); itr != adjList[temp.id].end(); itr++) // iterating through all neighbours 
        {
            ll nghID = itr->first;
            if (dist[nghID] > dist[temp.id] + adjList[temp.id][nghID] && !isVisited[nghID]) // updating distance if new one is lesser
            {
                dist[nghID] = dist[temp.id] + adjList[temp.id][nghID];
                parent[nghID] = temp.id;
                pq.push(make_pair(dist[nghID], nodesList[nghID]));
            }
        }
    }

    if (dist[dest.id] == INF) // this means destination is not connected to the source
    {
        cout << "\nNo Path Found\n";
    }
    else
    {
        cout << "\nDist: " << dist[dest.id] << endl;
        printPath(parent, nodesList, dest.id, source.id); // path printing
    }
}