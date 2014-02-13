package database.types;

import channel.Sokgraph;

public interface SokgraphSpecification extends SqlSpecification{
	boolean specified(Sokgraph s);
}
