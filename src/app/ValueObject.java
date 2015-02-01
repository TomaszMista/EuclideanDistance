package app;

/**
 * 
 * @author tomasz.mista
 *
 */
public class ValueObject {

	private DoubleNormal dn = null;
	private PointSet Q = null;
	private boolean isEmpty = false;
	
	public ValueObject(DoubleNormal dn, PointSet Q)
	{
		this.dn = dn;
		this.Q = Q;
		if(Q == null || Q.setSize()==0)
		{
			isEmpty = true;
		}
	}
	
	public DoubleNormal getDn() {
		return dn;
	}
	
	public void setDn(DoubleNormal dn) {
		this.dn = dn;
	}
	
	public PointSet getQ() {
		return Q;
	}
	
	public void setQ(PointSet q) {
		Q = q;
	}
	
	public boolean isEmpty() {
		return isEmpty;
	}
	
	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
}

