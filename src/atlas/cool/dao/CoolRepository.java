/**
 * 
 */
package atlas.cool.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import atlas.cool.annotations.CoolQueryRepository;
import atlas.cool.annotations.QueryParams;

@ApplicationScoped
/**
 * @author formica
 *
 */
public class CoolRepository {

	@Inject
	private EntityManager em;

	@Inject
	private Logger log;

	@Inject
	private CoolQueryRepository coolqry;

	/**
	 * 
	 */
	public CoolRepository() {
		// TODO Auto-generated constructor stub
	}

	public List<?> findCoolList(String qryname, Object[] params)
			throws CoolIOException {

		if (qryname.equals("none")) {
			throw new CoolIOException("Cannot find query...none");
		}
		try {
			Query q = getQuery(qryname, params);
			return q.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected Query getQuery(String qry, Object[] params) {
		QueryParams annparams = coolqry.getQuery(qry);
		String[] paramnames = annparams.getParams();
		Query q = em.createNamedQuery(qry);
		log.info("Getting parameter list of size " + paramnames.length
				+ " for query " + annparams.getQryname());
		for (int i = 0; i < paramnames.length; i++) {
			String key = paramnames[i];
			Object val = params[i];
			log.info("setting query parameter " + key+" value "+val);
			if (val == null && paramnames[i].equals("node")) {
				val = "%";
			} else if (val == null && paramnames[i].equals("tag")) {
				val = "%"; 
			}
			q.setParameter(key, val);
		}

		return q;
	}

}
