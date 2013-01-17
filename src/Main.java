// File: Main.java
// Package: delftalization
//
// Copyright (c) 2011 Michiel Meijer
import java.util.*;
import java.io.*;
 
/**
 * This is the main class for the delftalization application. It expects as its
 * first command line argument the name of a configuration file for example:
 * <PRE>
 * 9 7
 * bronze 6 2
 * iron 6 6
 * archery 2 3
 * horseback-riding 10 2
 * horse-archer 0 6
 * knights 6 12
 * mathematics 6 0
 * construction 8 4
 * currency 2 10
 * iron -> bronze
 * horse-archer -> archery
 * horse-archer -> horseback-riding
 * knights -> horseback-riding
 * knights -> iron
 * construction -> mathematics
 * currency -> mathematics
 * </PRE>
 * The first number of the first line tells it that 9 lines follow specifying
 * technologies. The second number on the first line tells it that next
 * 7 lines follow specifying how certain technologies depend on each other.
 * The 9 lines of technologies give a name for each technology followed by an
 * integer cost to develop it and an integer profit when developed. The
 * dependency lines name two previously declared technologies separated by
 * '->'. The application constructs a graph from this data to which a Ford-
 * Fulkerson algorithm is applied. The result of this algorithm (maximum
 * net revenue and selected technologies to reach that revenue) is then
 * printed to the screen.
 *
 * author  Michiel Meijer <m.meijer@cinis.com>
 * @version 2011.0327
 * @since   1.6
 */
public class Main {
 
    /**
     * Open a named source file and use its contents to create a graph.
     * The initialisation routine is protected against the most common errors
     * that can occur and will report the errors to System.err before exiting
     * with a non zero code.
     *
     * @param G     Graph object to be initialised
     * @param src   Name of source configuration file
     */
    protected static void initialise(Graph G,String src) {
        Integer techCount; // Number of technologies to read
        Integer depCount; // Number of dependencies to read
        Integer cost,profit; // Cost and profit
        Integer i; // General purpose iterator
        String firstTech,lastTech; // name of technologies
        String s; // general purpose string
        Scanner scan; // Input scanner
 
        try{
            scan=new Scanner(new File(src));
 
            // First two entirs are count the number of following lines
            techCount=scan.nextInt();
            depCount=scan.nextInt();
 
            // First section defines available technologies
            for(i=0;i<techCount;i++) {
                firstTech=scan.next();
                cost=scan.nextInt();
                profit=scan.nextInt();
 
                G.addTechnology(firstTech,profit,cost);
            }
 
            // Second section links depending technologies
            for(i=0;i<depCount;i++) {
                firstTech=scan.next();
                s=scan.next(); // this should be a "->" as per specification
                lastTech=scan.next();
 
                if (!s.equals("->")) // check specifications!
                    throw new InputMismatchException(
                            "Expected a separtor of form '->' not foud");
 
                G.addDependency(firstTech, lastTech);
            }
 
            scan.close();
        } catch (InputMismatchException e) {
            System.err.println("Configuratuon file '"+src+"' is malformed:");
            System.err.println(e.toString());
            System.exit(1);
        } catch (IllegalArgumentException e) {
            System.err.println("Configuration file '"+src+"' is inconsistent:");
            System.err.println(e.toString());
            System.exit(1);
        } catch (FileNotFoundException e) {
            System.err.println("Configuration file '"+src+"' was not found:");
            System.err.println(e.toString());
            System.exit(1);
        } catch (NoSuchElementException e) {
            System.err.println("Configuration file '"+
                                                    src+"' ended prematurely:");
            System.err.println(e.toString());
            System.exit(1);
        } catch (NullPointerException e) {
            System.err.println("Configuration file '"+
                                    src+"' causes access to undefined data:");
            System.err.println(e.toString());
            System.exit(1);
        } catch (OutOfMemoryError e) {
            System.err.println(
                    "Out of memory reading configuration file '"+src+"'");
            System.err.println(e.toString());
            System.exit(1);
        }
    }
 
    /**
     * Main entry point into the application. It executes the initialisation
     * and then calls the Graph created to calculate and print optimal values.
     * If all goes well it then exits with a code 0.
     *
     * @param args the command line arguments, the first of which should be a
     *              configuration file to read.
     */
    public static void main(String[] args) {
        String s;
        Graph G;
 
        s=(args.length>0)?(args[0]):("test.txt"); // Always provide valid string
        G=new Graph();
 
        initialise(G,s); // Construct the graph
        System.out.println("#version 1"); // required output
        G.optimise(); // Calculate and print optimal solution
 
        System.exit(0);
    }
 
}