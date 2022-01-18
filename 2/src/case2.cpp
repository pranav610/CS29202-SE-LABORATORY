#include <bits/stdc++.h>
#include <nodes.hpp>
#include <ways.hpp>
#include <case2.hpp>

using namespace std;

// will return K nearest nodes to the input node
void findKNearest(unordered_map<ll, nodes> &nodesList, ll id, int k)
{
    priority_queue<Pair, vector<Pair>, compareDist> pq; // pair is <ld, nodes> and compareDist is custom comparator
    unordered_map<ll, nodes>::iterator itr;
    for (itr = nodesList.begin(); itr != nodesList.end(); itr++) // finding distance of all nodes and adding them to the heap O(n)
    {
        if (itr->first == id)
            continue;
        pq.push(make_pair(crowFlyDist(itr->second.lat, itr->second.lon, nodesList[id].lat, nodesList[id].lon), itr->second));
    }
    for (int i = 0; i < k; i++) // printing 1st k nodes with least crow fly distance O(klogn)
    {
        cout << "\nK nearest nodes are:\n";
        cout << i + 1 << ".\n";
        printNode(pq.top().second);
        cout << "With Distance: " << pq.top().first << endl;
        pq.pop();
    }
}

// direct distance between two points using latitudes and longitudes
ld crowFlyDist(ld lat1, ld lon1, ld lat2, ld lon2)
{
    lat1 = lat1 * M_PI / 180.0;
    lon1 = lon1 * M_PI / 180.0;
    lat2 = lat2 * M_PI / 180.0;
    lon2 = lon2 * M_PI / 180.0;

    ld dlong = lon2 - lon1;
    ld dlat = lat2 - lat1;

    ld ans = 2 * asin(sqrt(pow(sin(dlat / 2), 2) + cos(lat1) * cos(lat2) * pow(sin(dlong / 2), 2))) ; // haversine formula

    ld R = 6371; // radius of sphere (in this case of earth in km)

    ans = ans * R;

    return ans;
}