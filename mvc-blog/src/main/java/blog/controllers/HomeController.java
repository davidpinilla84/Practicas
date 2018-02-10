package blog.controllers;

import static java.util.stream.Collectors.toList;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import blog.models.Post;
import blog.models.User;
import blog.services.NotificationService;
import blog.services.PostService;

@Controller
public class HomeController {

    @Autowired
    private PostService postService;

    @Autowired
    private NotificationService notificationService;

    @RequestMapping("/")
    public String home(Model model) {
        List<Post> latest5Posts = postService.findLatest5();
        model.addAttribute("latest5posts", latest5Posts);

        List<Post> latest3Posts = latest5Posts.stream()
                .limit(3).collect(toList());
        model.addAttribute("latest3posts", latest3Posts);

        return "index";
    }

    @RequestMapping("/posts/view/{id}")
    public String view(@PathVariable("id") Long id,
                       Model model) {
        Post post = postService.findById(id);

        if (post == null) {
            notificationService.addErrorMessage(
                    "Cannot find post: " + id);
            return "redirect:/";
        }

        model.addAttribute("post", post);
        return "/posts/index";
    }

    @RequestMapping("/posts/create")
    public String create(Model model) {
    	
    	Post post = new Post();
    	User user = new User();
    	user.setId(1L);
    	post.setAuthor(user);
    	post.setBody("BODY");
    	post.setDate(new Date());
    	post.setTitle("TITLE");
    	post.setId(1245L);
        //Post post = postService.create(new Post());

        model.addAttribute("post", post);
        return "/posts/index";
    }
    
    /**
     * Save product to database.
     *
     * @param product
     * @return
     */
    @RequestMapping(value = "post", method = RequestMethod.POST)
    public String savePost(Post post) {
        postService.savePost(post);
        return "redirect:/post/" + post.getId();
    }
    
    @RequestMapping("/users")
    public String users(Model model) {
        List<User> listUsers = postService.findAllUsers();
        model.addAttribute("users", listUsers);

        List<User> users3 = listUsers.stream()
                .limit(3).collect(toList());
        model.addAttribute("users3", users3);

        return "users";
    }
}