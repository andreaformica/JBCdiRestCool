package atlas.cool.rest.web;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import atlas.cool.dao.CoolDAO;
import atlas.cool.dao.CoolIOException;
import atlas.cool.rest.model.NodeGtagTagType;
import atlas.cool.rest.model.NodeType;
import atlas.cool.rest.model.SchemaNodeTagType;

/**
 * JAX-RS Example
 * 
 * This class produces a RESTful service to read the contents of the members
 * table.
 */
@Path("/cool")
@RequestScoped
public class CoolResourceRESTService {

	@Inject
	private CoolDAO cooldao;

	@Inject
	private Logger log;

	@GET
	@Produces("text/xml")
	@Path("/{schema}/{db}/nodes")
	public List<NodeType> listNodesInSchema(@PathParam("schema") String schema,
			@PathParam("db") String db) {
		
		log.info("Calling listNodesInSchema..."+schema+" "+db);
		List<NodeType> results = null;
		try {
			results = cooldao.retrieveNodesFromSchemaAndDb(schema+"%", db, "%");
			if (results == null) {
				// create a fake entry
				NodeType nt = new NodeType();
				nt.setNodeId(1L);
				nt.setNodeFullpath("this is a fake node");
				nt.setNodeTinstime(new Timestamp(new Date().getTime()));
				List<NodeType> _fakes = new ArrayList<NodeType>();
				_fakes.add(nt);
				results = _fakes;
			}
		} catch (CoolIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}
	
	
	@GET
	@Produces("text/xml")
	@Path("/{schema}/{db}/{node}/tags")
	public List<SchemaNodeTagType> listTagsInNodesSchema(@PathParam("schema") String schema,
			@PathParam("db") String db,@PathParam("node")String node) {
		
		log.info("Calling listTagsInNodeSchema..."+schema+" "+db+" "+node);
		List<SchemaNodeTagType> results = null;
		try {
			if (node.equals("all")) {
				node="%";
			} else {
				node = "%"+node+"%";
			}
			results = cooldao.retrieveTagsFromNodesSchemaAndDb(schema+"%", db, node, null);
		} catch (CoolIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}

	@GET
	@Produces("text/xml")
	@Path("/{schema}/{db}/{gtag}/trace")
	public List<NodeGtagTagType> listGlobalTagsTagsInNodesSchema(@PathParam("schema") String schema,
			@PathParam("db") String db,@PathParam("gtag") String gtag) {
		
		log.info("Calling listGlobalTagsTagsInNodesSchema..."+schema+" "+db);
		List<NodeGtagTagType> results = null;
		try {
			results = cooldao.retrieveGtagTagsFromSchemaAndDb(schema+"%", db, "%"+gtag+"%");
		} catch (CoolIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}

}
