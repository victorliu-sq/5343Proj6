package ast;

import java.io.PrintStream;
import java.util.*;

public class Program extends ASTNode {
    public final String funName; // function name
    public final int retType;   // function return type
    public final List<Decl> dList; // list of declarations
    public final List<Stmt> sList; // list of statements

    public Program(String i, int t, List<Decl> dl, List<Stmt> sl) {
        funName = i;
        retType = t;
        dList = dl;
        sList = sl;
    }

    public void print(PrintStream ps) {
        ps.println(Types.toString(retType) + " " + funName + "()\n{");
        for (Decl d : dList)
            d.print(ps);
        for (Stmt s : sList)
            s.print(ps, "  ");
        ps.println("}");
    }

    // CFG Analysis method called from main
    public void cfgAnalysis(PrintStream ps) {
        CFG cfg = constructCFG();
        cfg.printStats(ps);
        cfg.analyzeLoops(ps);
    }

    // Construct the CFG
    private CFG constructCFG() {
        CFG cfg = new CFG();

        // Step 1: Find leaders and map labels to indices
        Set<Integer> leaders = new HashSet<>();
        Map<String, Integer> label2StartIndex = new HashMap<>();

        // First instruction is a leader
        if (!sList.isEmpty()) {
            leaders.add(0);
        }

        // Identify leaders and populate label map
        for (int i = 0; i < sList.size(); i++) {
            Stmt stmt = sList.get(i);
            if (stmt instanceof LabelStmt) {
                String labelName = ((LabelStmt) stmt).labelName;
                label2StartIndex.put(labelName, i); // Map label to its index
            }
        }

        // Second loop: Add targets of jumps as leaders, with error reporting
        for (int i = 0; i < sList.size(); i++) {
            Stmt stmt = sList.get(i);
            if (stmt instanceof GotoStmt) {
                String target = ((GotoStmt) stmt).labelName;
                Integer targetIndex = label2StartIndex.get(target);
                leaders.add(targetIndex); // Add jump target
                if (i + 1 < sList.size()) {
                    leaders.add(i + 1); // After jump
                }
            } else if (stmt instanceof IfStmt) {
                IfStmt ifStmt = (IfStmt) stmt;
                if (ifStmt.thenStmt instanceof GotoStmt) {
                    String target = ((GotoStmt) ifStmt.thenStmt).labelName;
                    Integer targetIndex = label2StartIndex.get(target);
                    leaders.add(targetIndex); // Add conditional jump target
                }
                if (i + 1 < sList.size()) {
                    leaders.add(i + 1); // After jump
                }
            }
        }


        // Step 2: Create all blocks in order: ENTRY, basic blocks, EXIT
        List<Integer> leaderList = new ArrayList<>(leaders);
        Map<Integer, CFGNode> startIndex2Block  = new HashMap<>();
        Collections.sort(leaderList);

        // Initialize EXIT as the last block
        cfg.exit = new CFGNode("EXIT", leaderList.size(), leaderList.size());

        // Create basic blocks
        int blockNum = 1;
        for (int i = 0; i < leaderList.size(); i++) {
            int start = leaderList.get(i);
            int end = (i + 1 < leaderList.size()) ? leaderList.get(i + 1) - 1 : sList.size() - 1;
            CFGNode block = new CFGNode("B" + blockNum++, start, end);

            cfg.nodes.add(block);
            startIndex2Block.put(start, block);
        }

        for (int i = 0; i < cfg.nodes.size(); i++) {
            CFGNode block = cfg.nodes.get(i);
            int start = block.startIndex;
            int end = block.endIndex;
            Stmt lastStmt = sList.get(end);

            if (lastStmt instanceof GotoStmt) {
                String target = ((GotoStmt) lastStmt).labelName;
                Integer targetIndex = label2StartIndex.get(target);
                CFGNode targetBlock = startIndex2Block.get(targetIndex);
                block.addEdge(targetBlock);
            } else if (lastStmt instanceof IfStmt) {
                IfStmt ifStmt = (IfStmt) lastStmt;
                String target = ((GotoStmt) ifStmt.thenStmt).labelName;
                Integer targetIndex = label2StartIndex.get(target);
                CFGNode targetBlock = startIndex2Block.get(targetIndex);
                block.addEdge(targetBlock);

                CFGNode regularTarget;
                if (i + 1 < leaderList.size()) {
                    regularTarget = startIndex2Block.get(cfg.nodes.get(i + 1).startIndex);
                } else {
                    regularTarget = cfg.exit; // Connect to EXIT for return or last statement
                }
                block.addEdge(regularTarget);
            } else {
                CFGNode regularTarget;
                if (i + 1 < leaderList.size()) {
                    regularTarget = startIndex2Block.get(cfg.nodes.get(i + 1).startIndex);
                } else {
                    regularTarget = cfg.exit; // Connect to EXIT for return or last statement
                }
                block.addEdge(regularTarget);
            }
        }

        // Initialize ENTRY as the first block
        cfg.entry = new CFGNode("ENTRY", -1, -1);
        if (cfg.nodes.size() > 0) {
            cfg.entry.addEdge(cfg.nodes.get(0));
        }

        return cfg;
    }

    // CFG Node class
    private static class CFGNode {
        String name;              // e.g., "ENTRY", "B1", "EXIT"
        int startIndex;           // Starting index in sList
        int endIndex;           // Starting index in sList
        List<CFGNode> successors;
        List<CFGNode> predecessors; // NEW

        public CFGNode(String name, int startIndex, int endIndex) {
            this.name = name;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.successors = new ArrayList<>();
            this.predecessors = new ArrayList<>(); // NEW
        }

        public void addEdge(CFGNode to) {
            this.successors.add(to);
            to.predecessors.add(this); // Maintain reverse link
        }
    }

    // CFG class
    private static class CFG {
        List<CFGNode> nodes; // All nodes including ENTRY and EXIT
        CFGNode entry;
        CFGNode exit;

        public CFG() {
            nodes = new ArrayList<>();
            // ENTRY and EXIT will be created in Step 2
        }

        public void addNode(CFGNode node) {
            nodes.add(node);
        }

        public int getNodeCount() {
            return nodes.size() + 2;
        }

        public int getEdgeCount() {
            int edges = 0;
            for (CFGNode node : nodes) {
                edges += node.successors.size();
            }
            edges += entry.successors.size();
            return edges;
        }

        public void printStats(PrintStream ps) {
            ps.println("CFG NODES: " + getNodeCount());
            ps.println("CFG EDGES: " + getEdgeCount());
        }

        public void analyzeLoops(PrintStream ps) {
            // DFS setup
            Map<CFGNode, String> color = new HashMap<>();
            for (CFGNode node : nodes) {
                color.put(node, "WHITE");
            }
            color.put(entry, "WHITE");

            List<Edge> backEdges = new ArrayList<>();
            dfs(entry, color, backEdges);

            // Identify all loop nodes
            Set<CFGNode> nodesInLoops = new HashSet<>();
            for (Edge e : backEdges) {
                Set<CFGNode> loop = findNaturalLoop(e.from, e.to);
                nodesInLoops.addAll(loop);
            }

            ps.println("BACK EDGES: " + backEdges.size());
            ps.println("NODES IN LOOPS: " + nodesInLoops.size());
        }

        // DFS traversal to find back edges
        private void dfs(CFGNode node, Map<CFGNode, String> color, List<Edge> backEdges) {
            color.put(node, "GRAY");
            for (CFGNode succ : node.successors) {
                String succColor = color.getOrDefault(succ, "WHITE");
                if (succColor.equals("WHITE")) {
                    dfs(succ, color, backEdges);
                } else if (succColor.equals("GRAY")) {
                    // Found a back edge (retreating)
                    backEdges.add(new Edge(node, succ));
                }
            }
            color.put(node, "BLACK");
        }

        // Find the natural loop of a back edge n -> h
        private Set<CFGNode> findNaturalLoop(CFGNode n, CFGNode h) {
            Set<CFGNode> loop = new HashSet<>();
            Deque<CFGNode> worklist = new ArrayDeque<>();
//            System.out.println("From " + n.name + "; To " + h.name);
            loop.add(h);

            if (h != n) {
                loop.add(n);
                worklist.add(n);
                while (!worklist.isEmpty()) {
                    CFGNode m = worklist.pop();
                    for (CFGNode pred : m.predecessors) {
                        if (loop.add(pred)) {
//                            System.out.println("Added: " + pred.name);
                            worklist.push(pred);
                        }
                    }
                }
            }

            return loop;
        }

        // Represent a back edge
        private static class Edge {
            CFGNode from, to;
            public Edge(CFGNode f, CFGNode t) {
                this.from = f;
                this.to = t;
            }
        }
    }
}