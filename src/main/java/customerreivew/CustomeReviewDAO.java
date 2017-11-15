package customerreivew;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

public class CustomeReviewDAO {

HibernateTemplate template;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory factory)
	{
		template = new HibernateTemplate(factory);
		sessionFactory = factory;

	}
	
	public SessionFactory getSessionFactory()
	{
		return sessionFactory;

	}
	
	public void saveReview(CustomerReviewBean crb){
		template.save(crb);
	}
	
	// Validating customer review message
	public boolean validateReview(String reviewdes) throws Exception
	{
	
		Properties prop = new Properties();
		InputStream input = null;
		boolean messageValidity = true;
		
		// Reading the curse words from property file.
		try {

			input = new FileInputStream("config.properties");
			
			prop.load(input);
		
					
			StringTokenizer st = new StringTokenizer(prop.getProperty("cursewords"));
			while (st.hasMoreElements()) {
				
					if (reviewdes.contains(" "+st.nextToken()+" "))
					{
						messageValidity = false;
						throw new Exception("The review should be constructive with right words");
					}
					
					
			}
			
			
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return messageValidity;
	}
	
	public boolean validateReviewValue(int custReviewValue) throws Exception
	{

		if(custReviewValue < 0)
		{
			throw new Exception("Invalid Review Value");
		}
		
		return true; 
		
	}
	
	public List<CustomerReviewBean> getReviewCount (int productid,int startrange, int endrange)
	{	 
	 
		String query = "select count(product_id) from CustomerReviewBean where customer_review_value between " +startrange+ " and " +endrange+ " and product_id= " +productid;
		Session session = getSessionFactory().openSession();		
		@SuppressWarnings("unchecked")
		List<CustomerReviewBean> emplst = session.createQuery(query).list();
		return emplst;
	
	}
	
}
