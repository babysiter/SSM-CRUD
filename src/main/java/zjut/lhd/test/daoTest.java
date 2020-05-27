package zjut.lhd.test;

import com.github.pagehelper.PageInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import zjut.lhd.bean.Employee;
import zjut.lhd.controller.EmployeeController;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:springmvc.xml"})
public class daoTest {

	EmployeeController dm;
	MockMvc mockMvc;
	//这个属性必须通过上面那个注解拿到，通过他模拟
	@Autowired
	WebApplicationContext context;
	@Before
	public void initMockMvc(){
		MockMvcBuilders.webAppContextSetup(context);
	}
	@Test
	public void testPage() throws Exception{
		//模拟创建请求
		MvcResult mr = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "1"))
		.andReturn();
		//请求成功后，	可以从请求域拿到数据
		MockHttpServletRequest request = mr.getRequest();
		PageInfo pi = (PageInfo) request.getAttribute("allEmps");
		System.out.println("当前页码"+pi.getPageNum());
		System.out.println("总页码"+pi.getPages());
		System.out.println("总记录数："+pi.getTotal());
		System.out.println("在页面需要连续显示的页码");
		int[] nums = pi.getNavigatepageNums();
		for(int i=0;i<nums.length;i++)
			System.out.println(nums[i]);
		
		//获取员工数据
		List<Employee> le = pi.getList();
		for(Employee emp:le){
			System.out.println(emp.getEmpName()+emp.getGender()+emp.getDepartment().getDeptName());
		}
	}
}
