//////////////////////////////////////////////////////////////////////////////////////////
//
// Implementation of the TinkerPop OLTP Provider API for ArangoDB
//
// Copyright triAGENS GmbH Cologne and The University of York
//
//////////////////////////////////////////////////////////////////////////////////////////

package com.arangodb.tinkerpop.gremlin.client;

import com.arangodb.ArangoCursor;
import com.arangodb.tinkerpop.gremlin.structure.ArangoDBGraph;
import com.arangodb.tinkerpop.gremlin.structure.ArangoDBGraphVariables;

import java.util.Iterator;

/**
 * The VariableIterator is used to wrap and ArangoDB iterator and return ArangoDBGraphVariables that use a specific
 * GraphVariablesClient during.
 *
 * @author Horacio Hoyos Rodriguez (https://www.york.ac.uk)
 */

public class VariableIterator implements Iterator<ArangoDBGraphVariables> {

	private final ArangoCursor<ArangoDBGraphVariables> delegate;

	private final GraphVariablesClient client;

	/**
	 * Instantiates a new ArangoDB iterator.
	 *
	 * @param client 				the graph client
	 * @param delegate 				the delegate iterator
	 */
	public VariableIterator(GraphVariablesClient client, ArangoCursor<ArangoDBGraphVariables> delegate) {
		super();
		this.delegate = delegate;
		this.client = client;
	}

	@Override
	public boolean hasNext() {
		return delegate.hasNext();
	}

	@Override
	public ArangoDBGraphVariables next() {
		return delegate.next().useClient(client);
	}
	
}