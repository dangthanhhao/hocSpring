package springcoban;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		//IPerson a=new Vietnamese();
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("springcoban/vietnamese-config.xml");
		IPerson a= context.getBean("nguoiviet1",IPerson.class);
		
		
		System.out.println(a.talk());
		context.close();
	}

}
