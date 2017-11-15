package customerreivew;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;



public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ApplicationContext appContext = new FileSystemXmlApplicationContext("springconfig.xml");
		CustomeReviewDAO cusReviewDAO  = appContext.getBean("CustomerReviewDAO", CustomeReviewDAO.class);
		CustomerReviewBean crb = appContext.getBean("CustomerReviewBean", CustomerReviewBean.class);
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Select option 1) CreateReview 2) RetrieveReviewCount");
		int option = scanner.nextInt();
		
		boolean custReviewStatus = false;
		boolean custReviewValueStatus = false;
		String cusReviewDes = null;
		int custReviewValue = 0;
		
		if(option == 1)
		{
			System.out.println("Mr Customer create review for product A. Note :Review should be single line in 250 words");
			cusReviewDes = scanner.next();
			System.out.println("Mr Customer Enter review value between 1 to 5. ");
			custReviewValue = scanner.nextInt();
			
			
			try {
				 custReviewStatus = cusReviewDAO.validateReview(cusReviewDes);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				scanner.close();
				e.printStackTrace();
			}
			
			try {
				 custReviewValueStatus = cusReviewDAO.validateReviewValue(custReviewValue);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				scanner.close();
				e.printStackTrace();
			}
			
			if(custReviewStatus && custReviewValueStatus)
			{
				crb.setProduct_id(1);
				crb.setProduct_name("TV");
				crb.setCustomer_review_value(custReviewValue);
				crb.setReview_description(cusReviewDes);
				
				cusReviewDAO.saveReview(crb);
				
			}
			
			
		}
		else if (option == 2)
		{
			scanner.close();
			List <CustomerReviewBean> crblist = cusReviewDAO.getReviewCount(1,4,5);
			System.out.println(crblist.get(0));
		}
		else
		{
			System.out.println("Invalid input");
			scanner.close();
		}
	}

}
