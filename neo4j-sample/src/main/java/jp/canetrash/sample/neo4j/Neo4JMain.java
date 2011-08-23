package jp.canetrash.sample.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.EmbeddedGraphDatabase;

/**
 * @author tfunato
 * @see http://wiki.neo4j.org/content/Getting_Started_With_Java
 *
 */
public class Neo4JMain {
	public static void main(String args[]) {
		GraphDatabaseService graphDb = new EmbeddedGraphDatabase( "graphdb" );
		Transaction tx = graphDb.beginTx();
		try {
		Node firstNode = graphDb.createNode();
		Node secondNode = graphDb.createNode();
		Relationship relationship = firstNode.createRelationshipTo( secondNode, MyRelationshipTypes.KNOWS );

		firstNode.setProperty( "message", "Hello, " );
		secondNode.setProperty( "message", "world!" );
		relationship.setProperty( "message", "brave Neo4j " );
		tx.success();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		finally {
			tx.finish();
		}
	}
}
