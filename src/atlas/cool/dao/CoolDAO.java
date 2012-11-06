package atlas.cool.dao;

import java.util.List;

import atlas.cool.rest.model.NodeGtagTagType;
import atlas.cool.rest.model.NodeType;
import atlas.cool.rest.model.SchemaNodeTagType;

public interface CoolDAO {

	public List<NodeType> retrieveNodesFromSchemaAndDb(String schema, String db, String node) throws CoolIOException;
	public List<SchemaNodeTagType> retrieveTagsFromNodesSchemaAndDb(String schema, String db, String node, String tag) throws CoolIOException;
	public List<NodeGtagTagType> retrieveGtagTagsFromSchemaAndDb(String schema, String db, String gtag) throws CoolIOException;
}
