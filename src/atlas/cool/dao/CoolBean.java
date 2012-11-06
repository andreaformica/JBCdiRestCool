/**
 * 
 */
package atlas.cool.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import atlas.cool.rest.model.NodeGtagTagType;
import atlas.cool.rest.model.NodeType;
import atlas.cool.rest.model.SchemaNodeTagType;

@Named
@Stateless
/**
 * @author formica
 *
 */
public class CoolBean implements CoolDAO {

	@Inject
	private CoolRepository coolrep;
	
	@Inject
	private Logger log;

	/**
	 * 
	 */
	public CoolBean() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see atlas.cool.dao.CoolDAO#retrieveNodesFromSchemaAndDb(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<NodeType> retrieveNodesFromSchemaAndDb(String schema,
			String db, String node) throws CoolIOException {
		Object[] params = new Object[3];
		params[0] = schema;
		params[1] =db;
		params[2] =node;
		log.info("Using query "+NodeType.QUERY_FINDNODES+" with "+schema+" "+db+" "+node);
		List<NodeType> nodelist = (List<NodeType>) coolrep.findCoolList(NodeType.QUERY_FINDNODES,params);
		log.info("Retrieved a list of "+nodelist.size()+" nodes");
		return nodelist;
	}

	/* (non-Javadoc)
	 * @see atlas.cool.dao.CoolDAO#retrieveTagsFromNodesSchemaAndDb(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<SchemaNodeTagType> retrieveTagsFromNodesSchemaAndDb(
			String schema, String db, String node, String tag)
			throws CoolIOException {
		Object[] params = new Object[4];
		params[0] = schema;
		params[1] = db;
		params[2] = node;
		params[3] = tag;
		log.info("Using query "+SchemaNodeTagType.QUERY_FINDTAGS_IN_NODES+" with "+schema+" "+db+" "+node+" "+tag);
		List<SchemaNodeTagType> taglist = (List<SchemaNodeTagType>) coolrep.findCoolList(SchemaNodeTagType.QUERY_FINDTAGS_IN_NODES,params);
		return taglist;
	}

	/* (non-Javadoc)
	 * @see atlas.cool.dao.CoolDAO#retrieveGtagTagsFromSchemaAndDb(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<NodeGtagTagType> retrieveGtagTagsFromSchemaAndDb(String schema,
			String db, String gtag) throws CoolIOException {
		Object[] params = new Object[3];
		params[0] = schema;
		params[1] = db;
		params[2] = gtag;
		log.info("Using query "+NodeGtagTagType.QUERY_FINDGTAGS_TAGS_TRACE+" with "+schema+" "+db+" "+gtag);
		List<NodeGtagTagType> gtaglist = (List<NodeGtagTagType>) coolrep.findCoolList(NodeGtagTagType.QUERY_FINDGTAGS_TAGS_TRACE,params);
		return gtaglist;
	}

}
