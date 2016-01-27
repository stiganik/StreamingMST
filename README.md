# StreamingMST

## Building

The project was developed strictly using Eclipse Mars. It is immediately importable with Eclipse, and that is the recommended IDE. The project can be built using the native tools in Eclipse.

## Usage

Once the simulation program has been started, two parallel screens are shown to the user. The left one ("EDIT") is used to display the initial graph, and the right one ("RESULT") presents the minimum spanning tree of the graph. Just after starting up the program, both of these screens are empty.

### Input

The input graph that will be used for streaming and minimum spanning tree calculation can either be generated or imported from an external CSV file.

To generate a random graph,
1. Select File -> New Graph,
2. Insert the amount of nodes needed,
3. Click OK.

To read an existing graph as input from a CSV file,
1. Select File -> Open Graph,
2. Find the preferred CSV file with the necessary data,
3. Click OK.

The first line of the CSV file has to contain a comma-separated list of all of the nodes used. Every other line must contain a comma-separated triplet representing an edge, consisting of (1) the first vertex, (2) the second vertex, and (3) the weight of the edge.

### Simulation

Once a graph has been either generated or imported from an external source, the simulation can be started by pressing "Start" in the menu. Each step of the simulation is carried out with a 1 second delay. The simulation can be stopped at any time by using the "Stop" button in the menu.