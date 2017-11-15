package customerreivew;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer_review")
public class CustomerReviewBean {

			
		@Id
		private int product_id;
		private String review_description;
		private String product_name;
		private int customer_review_value;
		
		public int getProduct_id() {
			return product_id;
		}
		public void setProduct_id(int product_id) {
			this.product_id = product_id;
		}
		public String getReview_description() {
			return review_description;
		}
		public void setReview_description(String review_description) {
			this.review_description = review_description;
		}
		public String getProduct_name() {
			return product_name;
		}
		public void setProduct_name(String product_name) {
			this.product_name = product_name;
		}
		public int getCustomer_review_value() {
			return customer_review_value;
		}
		public void setCustomer_review_value(int customer_review_value) {
			this.customer_review_value = customer_review_value;
		}
		
	}

