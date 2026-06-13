package shuuriye.demovalidationca234.Controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shuuriye.demovalidationca234.DTO.createRequestDto;
import shuuriye.demovalidationca234.Service.EmployeeService;

@Controller
@RequestMapping("/emp")
public class EmployeeWebController {
    private final EmployeeService service;

    public EmployeeWebController(EmployeeService service) {
        this.service = service;
    }

    //requests  root url
    @GetMapping
    public String getHome(Model model){
        model.addAttribute("employee" , service.getAllEmployees());
        return "home";  //page name
    }
    @GetMapping("/form")
    public String showForm(Model model){
        model.addAttribute("employee" , new createRequestDto());
        return "form";
    }

    @PostMapping("/register")
    public String registerEmployee(@Valid
                                       @ModelAttribute("employee") createRequestDto request ,
                                   BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "form";
        }
         service.addEmployee(request);

         return "redirect:/emp";  //homepage
    }

    @PostMapping("/delete/{id}")
    public String deleteEmp(@PathVariable String id){
        service.removeEmployee(id);
        return "redirect:/emp";
    }

}
