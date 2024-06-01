package com.onlineLibrary.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.onlineLibrary.Entity.Books;
import com.onlineLibrary.Entity.User;
import com.onlineLibrary.Service.BookService;
import com.onlineLibrary.Service.Userservice;

import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes
public class UserController {
	@Autowired
	private Userservice serv;
	@Autowired
    public HttpSession session;
	@Autowired
	private BookService bookserv;
	@GetMapping("/guna")
	public String getmessage() {
//		model.addAttribute("message", "user project is ready to do");
		return "UserLogin code is working";
	}
	@GetMapping("/")
	public String getpage() {
		return "UserLogin";
	}
	@GetMapping("/login")
	
	public String loginuser(@RequestParam String email,@RequestParam String password ) {
		
		
		try {
			User res = serv.login(email, password);
			if(res.getEmail().equalsIgnoreCase("sudhakarreddy@55gmail.com") && (res.getPassword().equalsIgnoreCase("sudhakarreddy55"))) {
//				session.setAttribute("userdetails", res);
				
				session.setAttribute("userdetails", res);
				User u=(User) session.getAttribute("userdetails");
				System.out.println(u+" from admin page ");
				System.out.println("admin page ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				return "redirect:/adminsess";
			}
			else {
				session.setAttribute("userdetails", res);
				User u=(User) session.getAttribute("userdetails");
				System.out.println(u+" from user page _+++++++++++++)+++++++++++++++++++((((((((((((((((())))))))))))))))))");
				System.out.println("user Session  page");
				
				return "redirect:/sess";
			}
			
		}
		catch(Exception e) {
			return "UserLogin";
		}	
		
	}
	@GetMapping("/adminfirstsite")
	public String GotoAdmin(Model model) {
		User u=(User) session.getAttribute("userdetails");
		model.addAttribute("nana", u);
		return "Adminpage";
	}
	@GetMapping("/comebacktoAdmin")
	public String ComebackAdmin(Model model) {
		return "AdminFirstpage";
	}
	@GetMapping("/sess")
	
	public String usersession(Model model) {
		
		 User res = (User)session.getAttribute("userdetails");
		System.out.println(res+"gunaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		model.addAttribute("nana", res);
		return "Userhome";
	}
@GetMapping("/adminsess")
	
	public String Adminsession(Model model) {
		
		 User res = (User)session.getAttribute("userdetails");
		System.out.println(res+"annaaaaaaaaaaaaaaaanananannanannananananananananananananannnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnanaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		model.addAttribute("nana", res);
		return "AdminFirstpage";
	}
	@GetMapping("/home")
	public String getHome() {
		return "UserHome";
	}
	@GetMapping("/logoutuser")
	public String logoutuser() {
		session.removeAttribute("userdetails");
		return "UserLogin";
	}
	@PostMapping("/save")
	public String Registeruser(@ModelAttribute User user) {
		
		User resdata = serv.saveUser(user);
		session.setAttribute("userdetails", resdata);
		Object res = session.getAttribute("userdetails");
		System.out.println(res);
		return "redirect:/home";
	}
	@GetMapping("/user_register")
	public String getregsiterform() {
		return "Registerform";
	}
	@GetMapping("/updateuser")
	public String Updateuser(Model model) {
		User upuser = (User)session.getAttribute("userdetails");
		model.addAttribute("resuser", upuser);
		return "EditUser";
	}
	
	public List<User> getalluser(){
		List<User> all = serv.getallUsers();
		return all;
	}
   
	public void deletebyid(int id) {
		serv.deleteuserByid(id);
	}
	@GetMapping("/userbooks")
	public String userbooks(Model model) {
		List<Books> all = bookserv.getAll();
		model.addAttribute("allbooks", all);
		return "Useravailablebooks";
	}
	@RequestMapping("/addtomybook/{id}")
	public String addtolist(@PathVariable("id") int id) {
		Books book = bookserv.getBookbyId(id);
		User user = (User)session.getAttribute("userdetails");
//		List <Books> b1=new ArrayList<>();
//		b1.add(book);
//		System.out.println(book+" book from what user is clicked ");
//		System.out.println(b1+"list that is used in mapping");
		Set<Books> li = user.getListofbooks();
		li.add(book);
		user.setListofbooks(li);
		User  user2= serv.saveUser(user);
		session.removeAttribute("userdetails");
		session.setAttribute("userdetails", user2);
		System.out.println(user+" user from add to my book mapping method");		
		return "redirect:/mybook";
		
	}
	@GetMapping("/mybook")
	public String mybook(Model model) {
		User user = (User)session.getAttribute("userdetails");
		System.out.println(user+"checking**************************************");
		Set<Books> books = user.getListofbooks();
		model.addAttribute("mybooks", books);
		return "MyBooks";
		
	}
	
	@RequestMapping("/deletemybook/{id}")
	public String deletelistbook(@PathVariable("id")int id) {
		User user = (User)session.getAttribute("userdetails");
		System.out.println(user+"mapping ---------------------------------------------------------------");
		Set<Books> list = user.getListofbooks();
		for(Books b1:list) {
			System.out.println("Coming ______________-------------------@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@******************************");
			if(b1.getId()==id) {
				list.remove(b1);
				System.out.println(user);
				System.out.println("removed **********************************((((((((((((((((((((((((((((((((((())))))))))))))))))))))))))))))))))))))");
			}
		}
		User user3 = serv.saveUser(user);
		System.out.println(user3+"after deleted book list of books is this");
		session.removeAttribute("userdetails");
		session.setAttribute("userdetails", user3);
	
	return "redirect:/mybook";
	}
	@GetMapping("/night")
	public String Demo1() {
		return "demo";
	}
	@GetMapping("adminavailablebooks")
	public String AdminBooks(Model model) {
		List<Books> all = bookserv.getAll();
		model.addAttribute("allbooks", all);
		return "AdminAvailablebooks";
	}
	@GetMapping("/Adminmybook")
	public String adminmybook(Model model) {
		User user = (User)session.getAttribute("userdetails");
		System.out.println(user+"checking**************************************");
		Set<Books> books = user.getListofbooks();
		model.addAttribute("mybooks", books);
		return "Adminmybooks";
		
	}
	@GetMapping("/showbookbyid/{id}")
	public ModelAndView showlinkbyid(@PathVariable("id") int id) {
		Books book = bookserv.getBookbyId(id);
		String booklink = book.getBooklink();
		ModelAndView view = new ModelAndView("Showbook");
		view.addObject("booklink",booklink );
		return view;
	}
//	@GetMapping("/demo")
//	public String view() {
//		return "Showbook";
//	}
//	
	@GetMapping("/adminshowbookbyid/{id}")
	public ModelAndView adminshowlinkbyid(@PathVariable("id") int id) {
		Books book = bookserv.getBookbyId(id);
		String booklink = book.getBooklink();
		ModelAndView view = new ModelAndView("Adminpopup");
		view.addObject("booklink",booklink );
		return view;
	}
}

