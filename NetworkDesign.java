
import java.util.ArrayList; 
import java.util.Random; 
import java.util.Scanner; 
public class NetworkDesign { 
public static void ShortestPath(int a[][], int b[][], int c[][], int k, int nodes) { 
int shortest[][] = new int[nodes][nodes]; 
int adjMatrix[][] = new int[nodes][nodes]; 
int cost = 0; 
int total_edges = nodes*(nodes-1); 
String output = ""; 
for (int i = 0; i < nodes; i++) { 
for (int j = 0; j < nodes; j++) { 
shortest[i][j] = -1; 
adjMatrix[i][j] = 0; 
} 
} 
for (int m = 0; m < nodes; m++) { 
for (int i = 0; i < nodes; i++) { 
for (int j = 0; j < nodes; j++) { 
if (a[i][m] + a[m][j] < a[i][j]) { 
a[i][j] = a[i][m] + a[m][j]; 
shortest[i][j] = m; 
} 
} 
} 
} 
for (int i = 0; i < nodes; i++) { 
for (int j = 0; j < nodes; j++) { 
int node = shortest[i][j]; 
int src = i; 
while (node != -1) { 
c[src][node] = c[src][node] + b[src][node]; 
adjMatrix[src][node] = 1; 
src = node; 
node = shortest[src][j]; 
} 
c[src][j] += b[i][j]; 
adjMatrix[src][j] = 1; 
} 
} 
for (int i = 0; i < nodes; i++) { 
for (int j = 0; j < nodes; j++) { 
cost = cost + a[i][j] * c[i][j]; 
} 
} 
System.out.println("cost : " + cost); 
int nonZeroEdges = 0; 
for (int i = 0; i < nodes; i++) { 
for (int j = 0; j < nodes; j++) { 
if (c[i][j] != 0) { 
nonZeroEdges++; 
} 
} 
} 
float density = (float) nonZeroEdges / total_edges; 
System.out.println("Density : " + density); 
System.out.println("Output Adjacency Graph"); 
for (int i = 0; i < nodes; i++) { 
for (int j = 0; j < nodes; j++) { 
if (adjMatrix[i][j] == 1&&i==j) { 
adjMatrix[i][j]=0; 
} 
System.out.print(adjMatrix[i][j]+ " "); 
} 
System.out.println(""); 
} 
} 
public static void main(String[] args) { 
int numOfVert; 
int nodes=10; 
NetworkDesign nd = new NetworkDesign(); 
Scanner scan = new Scanner(System.in); 
System.out.println("Enter the value of N (nodes) : "); 
nodes=Integer.parseInt(scan.next()); 
System.out.println("Enter the random independent values in the range [0,1,2,3]: (21 numbers)"); 
String str=scan.next(); 
int temp[]= new int[str.length()]; 
for(int i=0;i<str.length();i++){ 
temp[i]=Integer.parseInt(str.valueOf(i)); 
} 
int bij[][] = new int[nodes][nodes]; 
int aij[][] = new int[nodes][nodes]; 
int k; 
int cij[][] = new int[nodes][nodes]; 
for (int i = 0; i < nodes; i++) { 
for (int j = 0; j < nodes; j++) { 
cij[i][j] = 0; 
} 
} 
ArrayList<Integer> sel = new ArrayList<>(); 
numOfVert = temp.length; 
for (int i = 0; i < numOfVert; i++) { 
for (int j = 0; j < numOfVert; j++) { 
bij[i][j] = Math.abs(temp[i] - temp[j]); 
} 
} 
for (int i = 0; i < 20; i++) { 
sel.add(i); 
} 
Random r = new Random(); 
System.out.println("Enter the value of K: "); 
k = scan.nextInt(); 
for (int i = 0; i < numOfVert; i++) { 
ArrayList<Integer> sel1 = new ArrayList<>(); 
while (sel1.size() != k) { 
int selected = sel.get(r.nextInt(sel.size())); 
if (!sel1.contains(selected)) { 
sel1.add(selected); 
} 
} 
for (int j = 0; j < numOfVert; j++) { 
if (i == j) 
aij[i][j] = 0; 
else if (sel1.contains(j)) 
aij[i][j] = 1; 
else 
aij[i][j] = 250; 
} 
} 
nd.ShortestPath(aij, bij, cij, k, nodes); 
} 
} 

