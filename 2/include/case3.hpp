using namespace std;

// adjacency list is unordered map of id and list of adjacent nodes stored in the form of unordered map
void createAdjList(unordered_map<ll, unordered_map<ll, ld>> &adjList, unordered_map<ll, nodes> &nodesList, vector<ways> waysList); 
// standard dijkstra algorithm
void dijkstra(unordered_map<ll, unordered_map<ll, ld>> &adjList, unordered_map<ll, nodes> &nodesList, nodes source, nodes dest);
// path printing using parent matrix
void printPath(unordered_map<ll, ll> &parent, unordered_map<ll, nodes> &nodesList, ll dest, ll source);