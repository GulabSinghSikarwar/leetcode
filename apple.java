import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class apple{

    
    public static void main(String[] args) {
        
        int nums[][] = { { 0, 1 }, { 0, 2 }, { 1, 4 }, { 1, 5 }, { 2, 3 }, { 2, 6 } };
        boolean [] hasApple={false,false,true,false,true,true,false};
        List <Boolean> ha=new ArrayList<>();
        for (Boolean boolean1 : hasApple) {
            
            ha.add(boolean1);

        }
        System.out.println(minTime(0, nums, ha));


    }


    public static  int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        
        Map <Integer, Boolean> visited =new HashMap<>();
        Map<Integer , List <Integer>> graph=createGraph(edges);
        int ans =dfs(visited , hasApple , 0, graph, 0);




        
     
        return ans;
        
    }


    private static int dfs(Map<Integer, Boolean> visited, List<Boolean> hasApple, int node,
            Map<Integer, List<Integer>> graph, int cost ) {

                 boolean visited_current_status =visited.getOrDefault(node, false);

                 if(visited_current_status) return 0 ;

                 visited.put(node, true);


                 int childCost =0;
                 
                 for (int n : graph.getOrDefault(node, new ArrayList<>())) {
                    
                    childCost+=dfs(visited, hasApple, n, graph, 2);

                 }
                 
                 
                 if(childCost==0 && hasApple.get(node)==false)
                 return 0 ;


                  return childCost+cost;


        
    }


    private static Map<Integer, List<Integer>> createGraph(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int u =edges[i][0];
            int v =edges[i][1];
            
            List<Integer> list = graph.getOrDefault(u, new ArrayList<>());
         
            list.add(v);

            graph.put(u, list);

            list =graph.getOrDefault(v, new ArrayList<>());
            list.add(u);

            graph.put(v,list );
            
        }
        System.out.println(graph);
        return graph;
    }
}   