package sg.edu.nus.iss.workshop12.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import sg.edu.nus.iss.workshop12.exception.InvalidNumberException;
import sg.edu.nus.iss.workshop12.model.Generate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Controller
public class GeneratorController {
    private Logger logger = LoggerFactory.getLogger(GeneratorController.class);

    @GetMapping("/")
    public String showGenerateForm(Model model){
            // instantiate the Generate class where it represent the form
            // of the generate html page
            Generate generate = new Generate();
            // set the generate class into the page scope of the generate html 
            model.addAttribute("generateObj", generate);
            // forward this endpoint to the page "generate.html"
            return "generatePage";
    }

    @PostMapping("/generate")
    public String generateNumbers(@ModelAttribute Generate generate, Model model){
        try{
                logger.info("From the form " + generate.getNoOfNumberToRandomize());

                int numberRandomNUmber = generate.getNoOfNumberToRandomize();

                if (numberRandomNUmber > 30){
                    throw new InvalidNumberException();
                }

                List<String> selectedImg = new ArrayList<String>();
                Random randNum = new Random();

                Set<Integer> uniqueGeneratedRandNumSet = new LinkedHashSet<Integer>();

                while(uniqueGeneratedRandNumSet.size() < numberRandomNUmber){
                    Integer resultOfRandNumbers = 
                            randNum.nextInt(generate.getNoOfNumberToRandomize() +1);
                    uniqueGeneratedRandNumSet.add(resultOfRandNumbers);
                }

                Iterator<Integer> it = uniqueGeneratedRandNumSet.iterator();
                Integer currentElem = null;

                while(it.hasNext()){

                        currentElem = it.next();
                        logger.info("currentElem >" + currentElem);

                        selectedImg.add("number" + currentElem.intValue() + ".jpg");
                }
                
                model.addAttribute("randNumsResult", selectedImg.toArray());
                model.addAttribute("numInputByUser", numberRandomNUmber);

        }catch(InvalidNumberException e){
                model.addAttribute("errorMessage", "Exceed 30");

                return "error";
        }

        return "result";
    }

    
}
