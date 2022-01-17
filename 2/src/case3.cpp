#include <bits/stdc++.h>
#include <nodes.hpp>
#include <ways.hpp>
#include <case2.hpp>
#include <case3.hpp>

const long double INF = 1e9 + 10;

using namespace std;

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

    pq.push(make_pair(0, source));
    dist[source.id] = 0;

    while (!pq.empty())
    {
        nodes temp = pq.top().second;
        pq.pop();

        unordered_map<long long int, long double>::iterator itr;
        for (itr = adjList[temp.id].begin(); itr != adjList[temp.id].end(); itr++)
        {
            long long int nghID = itr->first;
            if (dist[nghID] > dist[temp.id] + adjList[temp.id][nghID])
            {
                if ()
                {
                }
            }
        }
    }
}