/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicetwo;

import com.servicetwo.entity.request.SubjectRequest;
import com.servicetwo.entity.request.UserRequest;
import com.servicetwo.entitys.News;
import com.servicetwo.entitys.Subject;
import com.servicetwo.entitys.User;
import com.servicetwo.repository.NewsRepository;
import com.servicetwo.repository.SubjectRepository;
import com.servicetwo.repository.UserRepository;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author yanni
 */
@Component
public class Seeder implements ApplicationRunner {

//    private UserRepository userRepository;
//
//    @Autowired
//    public Seeder(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public void run(ApplicationArguments aa) {
//    }
    
    

    private SubjectRepository subjectRepository;
    private SubjectRequest subjectRequest;
    private NewsRepository newsRepository;
    private UserRequest userRequest;
    private UserRepository userRepository;
    
    @Autowired
    public Seeder(SubjectRepository subjectRepository, NewsRepository newsRepository, UserRepository userRepository)
    {
        subjectRequest = new SubjectRequest();
        userRequest = new UserRequest();
        this.subjectRepository = subjectRepository;
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments aa) {
        
        
        
        for(Subject s: subjectRequest.getAllFromOne())
        {
            Subject toPersist = new Subject(s.getNaam(), s.getOmschrijving(), s.getAgeLimit());
            subjectRepository.save(toPersist);
            String ss = "Debug";
        }
        
        
        
        for(User u: userRequest.getAllFromOne())
        {
            User toPersist = new User(u.getName(), u.getMail(), u.getPassword(), u.getAge());
            for(Subject s: userRequest.getAllSubjectsPerUserFromOne(u.getName()))
            {
                List<Subject> toAdd = new ArrayList<>();
                Subject addHere = subjectRepository.findSingleSubjectByNaam(s.getNaam());
                toAdd.add(addHere);
                toPersist.setFollowingSubjects(toAdd);
//                if(subjectRepository.findSingleSubjectByNaam(s.getNaam()) == null)
//                {
//                    subjectRepository.save(s);
//                }
//                else
//                    subjectRepository.save(s);
            }
            userRepository.save(toPersist);
        }
        
        List<Subject> subjects = subjectRepository.findAll();
        
        //Algemeen nieuws (Subjects.get(0))
        News algemeenEen = new News("MAN BEROOFT BANK", "In Tilburg is op zaterdagmiddag een bank beroofd door een 41 jarige man.",subjects.get(0));
        News algemeenTwee = new News("Kind redt hond", "In Tilburg sprong op maandagmiddag een dapper kind in de sloot om een hond te redden.",subjects.get(0));
        News algemeenDrie = new News("V&D toch failliet", "Vandaag is bekend geworden dat V&D haar deuren toch gaat sluiten.",subjects.get(0));
        News algemeenVier = new News("WERELDRECORD VERBROKEN!!!", "Vrouw eet heetste pepper ter wereld.",subjects.get(0));
        newsRepository.save(algemeenEen);
        newsRepository.save(algemeenTwee);
        newsRepository.save(algemeenDrie);
        newsRepository.save(algemeenVier);
        
        //Sport nieuws (Subjects.get(1))
        News SportEen = new News("Cubs Rugby Club Tilburg kampioen!", "In Tilburg is op zaterdagmiddag het cubsteam kampioen geworden.",subjects.get(1));
        News SportTwee = new News("Willen II speelt weer slecht", "Willem II heeft weer verloren, het gaat niet best.",subjects.get(1));
        News SportDrie = new News("Rugby Club Tilburg Heren II winnen van Breda", "De heren van RCT hebben met 12 - 79 gewonnen van Breda.",subjects.get(1));
        News SportVier = new News("Warandeloop weer een succes", "Ook dit jaar is de warandeloop weer drukbezocht.",subjects.get(1));
        newsRepository.save(SportEen);
        newsRepository.save(SportTwee);
        newsRepository.save(SportDrie);
        newsRepository.save(SportVier);
        
        //Tech nieuws (Subjects.get(2))
        News TechEen = new News("OnePlus 6 komt later dit jaar uit.", "OnePlus heeft aangekondigd haar telefoon in mei op de markt te brengen.",subjects.get(2));
        News TechTwee = new News("Red Dead Redemption realease bekend.", "Red Dead Redemption komt eind dit jaar nog uit.",subjects.get(2));
        News TechDrie = new News("iPhone 10 krijgt glazen achterkant", "De iPhone 10 wordt compleet van glas, zelfs de achterkant.",subjects.get(2));
        News TechVier = new News("MSI gaat door met laptops te produceren", "Het gerucht ging dat MSI zou stoppen met laptops te maken, dit is echter niet waar.",subjects.get(2));
        newsRepository.save(TechEen);
        newsRepository.save(TechTwee);
        newsRepository.save(TechDrie);
        newsRepository.save(TechVier);
        //Dieren nieuws (Subjects.get(3))
        News DierenEen = new News("Teckel blijft een populaire hond", "Ook dit jaar is de Teckel weer razendpopulair. Gemiddeld 20% van de Nederlandse huishoudens is in het gezelschap van deze vrolijke viervoeter.",subjects.get(3));
        News DierenTwee = new News("Familie stapt uit in Safaripark De Beeksebergen", "Een Frans gezin besloot uit te stappen in het safarigedeelte van de Beekse Bergen, daar ontsnapten ze ter nauwernood aan de dood.",subjects.get(3));
        News DierenDrie = new News("Soep is goed voor katten", "Onderzoek toont aan dat soep goed is voor katten. Dagelijks een portie vissoep houdt uw kat jong en gezond.",subjects.get(3));
        News DierenVier = new News("Tijger pakt man in India", "In India is eerder vandaag een man aangevallen door een tijger, de toestand van de man is nog onbekend.",subjects.get(3));
        newsRepository.save(DierenEen);
        newsRepository.save(DierenTwee);
        newsRepository.save(DierenDrie);
        newsRepository.save(DierenVier);
        
        
        for(Subject s: subjects)
        {
            News n = new News();
            n.setTitel(s.getNaam() + " in het nieuws");
            n.setContent(s.getNaam() + " is de content.");
            n.setSubject(s);
            newsRepository.save(n);
        }
    }
}
