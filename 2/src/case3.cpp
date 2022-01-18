#include <bits/stdc++.h>
#include <nodes.hpp>
#include <ways.hpp>
#include <case1.hpp>
#include <case2.hpp>
#include <case3.hpp>

const long double INF = 1e9 + 10;

using namespace std;

void printPath(unordered_map<long long int, long long int> &parent, unordered_map<long long int, nodes> &nodesList, long long int dest, long long int source)
{
    vector<nodes> path;
    cout << "Following is Path for the given source and destination: \n";
    path.insert(path.begin(), nodesList[dest]);
    nodes temp = nodesList[parent[dest]];
    while (temp.id != source)
    {
        path.insert(path.begin(), temp);
        temp = nodesList[parent[temp.id]];
    }
    path.insert(path.begin(), nodesList[source]);
    cout << "Path contains " << path.size() << " nodes. Do you want to print all of them ? Enter 1 to print path, 0 to skip path printing\n";
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

void createAdjList(unordered_map<long long int, unordered_map<long long int, long double>> &adjList, unordered_map<long long int, nodes> &nodesList, vector<ways> waysList)
{
    for (int i = 0; i < waysList.size(); i++)
    {
        for (int j = 0; j < waysList[i].wayPoints.size() - 1; j++)
        {
            long double wt = crowFlyDist(waysList[i].wayPoints[j].lat, waysList[i].wayPoints[j].lon, waysList[i].wayPoints[j + 1].lat, waysList[i].wayPoints[j + 1].lon);
            adjList[waysList[i].wayPoints[j].id][waysList[i].wayPoints[j + 1].id] = wt;
            adjList[waysList[i].wayPoints[j + 1].id][waysList[i].wayPoints[j].id] = wt;
        }
    }
}

void dijkstra(unordered_map<long long int, unordered_map<long long int, long double>> &adjList, unordered_map<long long int, nodes> &nodesList, nodes source, nodes dest)
{
    priority_queue<Pair, vector<Pair>, CompareDist> pq;
    unordered_map<long long int, long double> dist;
    unordered_map<long long int, bool> isVisited;
    unordered_map<long long int, long long int> parent;

    unordered_map<long long int, nodes>::iterator itr;
    for (itr = nodesList.begin(); itr != nodesList.end(); itr++)
    {
        isVisited[itr->first] = false;
        dist[itr->first] = INF;
        parent[itr->first] = -1;
    }

    pq.push(make_pair(0, source));
    dist[source.id] = 0;

    while (!pq.empty())
    {
        nodes temp = pq.top().second;
        isVisited[temp.id] = true;
        pq.pop();

        unordered_map<long long int, long double>::iterator itr;
        for (itr = adjList[temp.id].begin(); itr != adjList[temp.id].end(); itr++)
        {
            long long int nghID = itr->first;
            if (dist[nghID] > dist[temp.id] + adjList[temp.id][nghID] && !isVisited[nghID])
            {
                dist[nghID] = dist[temp.id] + adjList[temp.id][nghID];
                parent[nghID] = temp.id;
                pq.push(make_pair(dist[nghID], nodesList[nghID]));
            }
        }
    }

    if (dist[dest.id] == INF)
    {
        cout << "\nNo Path Found\n";
    }
    else
    {
        cout << "\nDist: " << dist[dest.id] << endl;
        printPath(parent, nodesList, dest.id, source.id);
    }
}