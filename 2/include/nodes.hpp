using namespace std;

class nodes
{
public:
    string name;
    long long int id;
    double lat;
    double lon;
};

void storeInput(vector<nodes> nodesList);
void searchName(vector<nodes> nodesList);