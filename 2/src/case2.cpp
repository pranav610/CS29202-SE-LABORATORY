#include <bits/stdc++.h>
#include <nodes.hpp>
#include <ways.hpp>
#include <case2.hpp>
#include <rapidxml.hpp>
#include <rapidxml_utils.hpp>

using namespace rapidxml;
using namespace std;

void findKNearest(unordered_map<long long int, nodes> &nodesList, long long int id, int k)
{   
    priority_queue<Pair, vector<Pair>, CompareDist> pq;
    unordered_map<long long int, nodes>::iterator itr;
    for (itr = nodesList.begin(); itr != nodesList.end(); itr++)
    {   
        if(itr->first == id)
            continue;
        pq.push(make_pair(crowFlyDist(itr->second.lat,itr->second.lon,nodesList[id].lat,nodesList[id].lon), itr->second));
    }
    for(int i=0; i<k; i++){
        cout << "K nearest nodes are:\n";
        cout << i+1 << ".\n";
        printNode(pq.top().second);
        cout << "With Distance: " << pq.top().first << endl;
        pq.pop();
    }
}

long double toRadians(long double degree)
{
    return M_PI * degree / 180.0;
}
long double crowFlyDist(long double lat1, long double lon1, long double lat2, long double lon2)
{
    lat1 = toRadians(lat1);
    lon1 = toRadians(lon1);
    lat2 = toRadians(lat2);
    lon2 = toRadians(lon2);

    long double dlong = lon2 - lon1;
    long double dlat = lat2 - lat1;

    long double ans = pow(sin(dlat / 2), 2) + cos(lat1) * cos(lat2) * pow(sin(dlong / 2), 2);

    ans = 2 * asin(sqrt(ans));

    long double R = 6371;

    ans = ans * R;

    return ans;
}