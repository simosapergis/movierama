package com.example.movierama.config;

import com.example.movierama.movie_opinion.MovieOpinion;
import com.example.movierama.movie_opinion.MovieOpinionRepository;
import com.example.movierama.movie.Movie;
import com.example.movierama.movie.MovieRepository;
import com.example.movierama.user.User;
import com.example.movierama.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class MovieramaConfig {

    @Bean
    CommandLineRunner commandLineRunner(MovieRepository movieRepository, UserRepository userRepository, MovieOpinionRepository movieOpinionRepository) {
        return args -> {
            if (userRepository.findAll().size() > 0) {
                return;
            }
            //password : user1234
            final User user1 = new User("user1@test.com", "$2a$10$aFZXXmzrebzvdHk0D/Oe8.cgiZrmK0qWvAjhcL8wbLlUZGz8dQ38m", "Frank", "Miller");
            final User user2 = new User("user2@test.com", "$2a$10$aFZXXmzrebzvdHk0D/Oe8.cgiZrmK0qWvAjhcL8wbLlUZGz8dQ38m", "Jason", "Momoa");
            final User user3 = new User("user3@test.com", "$2a$10$aFZXXmzrebzvdHk0D/Oe8.cgiZrmK0qWvAjhcL8wbLlUZGz8dQ38m", "Mark", "Stores");
            final User user4 = new User("user4@test.com", "$2a$10$aFZXXmzrebzvdHk0D/Oe8.cgiZrmK0qWvAjhcL8wbLlUZGz8dQ38m", "Bob", "Michael");
            final User user5 = new User("user5@test.com", "$2a$10$aFZXXmzrebzvdHk0D/Oe8.cgiZrmK0qWvAjhcL8wbLlUZGz8dQ38m", "Simos", "Apergis");
            final User user6 = new User("user6@test.com", "$2a$10$aFZXXmzrebzvdHk0D/Oe8.cgiZrmK0qWvAjhcL8wbLlUZGz8dQ38m", "Jim", "MIller");
            final User user7 = new User("user7@test.com", "$2a$10$aFZXXmzrebzvdHk0D/Oe8.cgiZrmK0qWvAjhcL8wbLlUZGz8dQ38m", "Nick", "Diaz");
            final User user8 = new User("user8@test.com", "$2a$10$aFZXXmzrebzvdHk0D/Oe8.cgiZrmK0qWvAjhcL8wbLlUZGz8dQ38m", "Steve", "Papa");
            final User user9 = new User("user9@test.com", "$2a$10$aFZXXmzrebzvdHk0D/Oe8.cgiZrmK0qWvAjhcL8wbLlUZGz8dQ38m", "John", "Digweed");
            final User user10 = new User("user10@test.com", "$2a$10$aFZXXmzrebzvdHk0D/Oe8.cgiZrmK0qWvAjhcL8wbLlUZGz8dQ38m", "Nate", "Diaz");

            userRepository.saveAll(List.of(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10));

            final Movie movie1 = new Movie("Top Gun", LocalDateTime.now().minusDays(10), user10, """
                    After more than thirty years of service as one of the Navy's top aviators, Pete 'Maverick' Mitchell is where he belongs, pushing the envelope as a courageous test pilot and dodging the advancement in rank that would ground him. When he finds himself training a detachment of Top Gun graduates for a specialised mission the likes of which no living pilot has ever seen, Maverick encounters Lt Bradley Bradshaw, call sign 'Rooster', the son of Maverick's late friend and Radar Intercept Officer Lt Nick Bradshaw, aka 'Goose.' Facing an uncertain future and confronting the ghosts of his past, Maverick is drawn into a confrontation with his own deepest fears, culminating in a mission that demands the ultimate sacrifice from those who will be chosen to fly it.
                    """);
            final Movie movie2 = new Movie("The Shooter", LocalDateTime.now().minusDays(20), user1, """
                            Force Recon sniper Gunnery Sergeant Bob Lee Swagger and his spotter Corporal Donnie Fenn are in Eritrea to cover the retreat of an allied convoy after a mission. However, enemy militia vehicles and a helicopter gunship ambush Swagger and Fenn, and the Marines' CIA support team abandons them, resulting in Fenn's death. Three years later, a retired Swagger lives in isolation in the mountains of Wyoming with a pet dog. At the office of a private military company in Langley, Virginia, US Army Colonel Isaac Johnson and his associates, Payne and Dobbler, review Swagger's file. Johnson approaches Swagger and requests him to use his skills and expertise to thwart a possible attempt on the President's life that intelligence sources have uncovered.");
                    """);
            final Movie movie3 = new Movie("The Wall", LocalDateTime.now().minusDays(24), user7, """
                            At the close of the Iraq War, U.S. Army Staff Sergeant Shane Matthews, a sniper, along with his spotter, Sergeant Allen Isaac, are assigned to the 51st ODA, are sent to investigate a pipeline construction site in the desert of the country where contractors and their security detail have all fallen victim to a sniper. The pair patiently wait 22 hours on overwatch before determining that the site is clear. Matthews proceeds to investigate the site, but is shot by famed Iraqi sniper nicknamed “Juba. Isaac tries to rescue the dying Matthews, but he is also wounded in the right knee and has his radio damaged and his water bottle destroyed in the process 
                    """);
            final Movie movie4 = new Movie("American Gangster", LocalDateTime.now().minusDays(0), user2, """
                    In 1968, Frank Lucas is the right-hand man of Harlem mob boss Ellsworth "Bumpy" Johnson. When Johnson dies of a heart attack, Frank enters the heroin trade, buying directly from producers in Thailand and smuggling it into the U.S. through returning Vietnam War servicemen. Frank sells his heroin under the brand "Blue Magic", whose affordability and purity make it incredibly popular, eliminating much of his competition.               
                    Newark detective and aspiring lawyer Richie Roberts is ostracized in his precinct after handing in almost $1 million that he found in a mobster's car. After his outcasted and addicted partner overdoses on Blue Magic, Captain Lou Toback puts Roberts in charge of a special task force that targets major local drug suppliers. Roberts is also depicted having a bitter divorce battle with his ex-wife over his infidelity.
                    """);
            final Movie movie5 = new Movie("Hello World", LocalDateTime.now().minusDays(1), user10, """
                    Set in Kyoto 2027, the Japanese government has made plans to collect and preserve the city's natural architecture and culture through drones in real time, storing all its data in an infinite-capacity quantum computer known as Alltale. Naomi Katagaki is an indecisive high school student living in Kyoto who harbors a love for reading. One day after school, a mysterious yatagarasu steals his library book and in an attempt to get it back, he meets a strange man that appears out of nowhere. This man, whom only Naomi can see, is revealed to be himself from 10 years later, now grown up and an adult.
                    """);
            final Movie movie6 = new Movie("Earthquake", LocalDateTime.now().minusDays(3), user6, """
                    On his way to work, former football star Stewart Graff, having just fought with his wife Remy, visits Denise Marshall, an actress who is the widow of one of his friends. He drops off an autographed football for her son Corry.           
                    A mild earthquake jolts the Los Angeles metro area. At the California Seismological Institute (CSI), Walter Russell has calculated that Los Angeles will suffer a major earthquake within the next few days. Scientists at the CSI debate whether to go public with their prediction of a major quake. Fearful their funding will be jeopardized, they decide only to alert the National Guard and police so that they can be ready to mobilize.
                    """);
            final Movie movie7 = new Movie("The Prestige", LocalDateTime.now().minusDays(4), user3, """
                    The movie opens with Cutter speaking to a young girl about magic and how the trick is engineered. As he speaks, we see cut scenes to the past and then Cutter's explanation to the little girl becomes his explanation to a court house in which Borden is on trial.
                    Lord Caldlow's solicitor seeks out Borden in jail to negotiate his secret for his daughter's safety.
                    In 1890s London, Robert Angier and Alfred Borden worked as plants for a magician under the mentorship of stage engineer John Cutter. During a water tank trick, Angier's wife, Julia, is unable to unbind her hands, fails to escape and drowns. Angier, devastated, blames Borden for using the incorrect knot. When Angier asks Borden which knot he used, Borden doesn't know. The two become bitter enemies and rivals. Angier and Borden launch their own magic careers, with Angier working with Cutter, and Borden with the mysterious Fallon.
                    """);
            final Movie movie8 = new Movie("Terminator 3: Rise of the Machines", LocalDateTime.now().minusDays(1), user4, """
                    Ten years after destroying Cyberdyne Systems, John Connor has been living as a nomad following the death of his mother, Sarah, to hide from the malevolent artificial intelligence Skynet, despite a war between humans and machines not happening in 1997, as foretold. Unable to locate John in the past, Skynet sends the T-X, an advanced prototype shapeshifting Terminator made of virtually impervious liquid metal, back in time to John's present in Los Angeles, to instead kill his future allies in the human resistance. The human resistance sends back a reprogrammed T-850 Terminator, a less-advanced metal endoskeleton covered in living human flesh, to protect John and his future wife Kate Brewster.
                    """);
            final Movie movie9 = new Movie("Don't Be Afraid of the Dark", LocalDateTime.now().minusDays(11), user9, """
                    At Blackwood Manor in Providence County, Rhode Island in the 19th century, renowned wildlife painter Lord Emerson Blackwood summons his housekeeper to the basement where he reluctantly bludgeons her to death. He removes her teeth, as well as his own, and offers them to mysterious creatures inside an ash pit within an old fireplace; the creatures reject his offer and demand only the teeth of children. Blackwood begs for them to give back his kidnapped son, only to be dragged in by the creatures.
                    """);
            final Movie movie10 = new Movie("Fight Club", LocalDateTime.now().minusDays(30), user5, """
                    In the Second Age of Middle-earth, the lords of Elves, Dwarves, and Men are given Rings of Power. Unbeknownst to them, the Dark Lord Sauron forges the One Ring in Mount Doom, instilling into it a great part of his power, to dominate the other Rings so he might conquer Middle-earth. A final alliance of Men and Elves battles Sauron's forces in Mordor. Isildur of Gondor severs Sauron's finger and the Ring with it, thereby vanquishing Sauron and returning him to spirit form. With Sauron's first defeat, the Third Age of Middle-earth begins. The Ring's influence corrupts Isildur, who takes it for himself and is later killed by Orcs. The Ring is lost in a river for 2,500 years until it is found by Gollum, who owns it for over four and a half centuries. The ring abandons Gollum and it is subsequently found by a hobbit named Bilbo Baggins, who is unaware of its history.
                    """);
            final Movie movie11 = new Movie("The Lord of the Rings", LocalDateTime.now().minusDays(10), user8, """
                    The Narrator (who is not named in the movie) is a chronic insomniac who is unfulfilled both by his job as an automobile recall specialist and the material wealth it affords him. As a substitute for therapy, he attends support groups for problems he doesn't really have, such as alcoholism and cancer. Another impostor, Marla Singer, begins attending the same groups. Her presence is taken by the Narrator as a constant reminder of his dishonesty, interfering with the therapeutic effect he's after. He confronts Marla, and proposes they divide group attendance, to which she grudgingly agrees.
                    """);
            final Movie movie12 = new Movie("King Arthur", LocalDateTime.now().minusDays(0), user10, """
                    In the 5th century AD, the declining Roman Empire is withdrawing from Britannia, where the native Woads, led by Merlin, stage an insurgency. A group of Sarmatian knights and their half-British Roman commander Artorius Castus, known as "Arthur", have fulfilled their duties to Rome and are preparing to return home. Arthur himself plans to continue his career in Rome until Bishop Germanus orders them to complete one final mission: evacuate an important Roman family from north of Hadrian's Wall, saving them from an advancing army of invading Saxons led by the ruthless Cerdic and his son, Cynric. Alecto, the son of the family patriarch, is a viable candidate to be a future Pope. Arthur and his remaining men – Lancelot, Tristan, Galahad, Bors, Gawain, and Dagonet – reluctantly accept the mission.
                    """);
            final Movie movie13 = new Movie("Hush", LocalDateTime.now().minusDays(1), user6, """
                    Deaf-mute horror author Maddie Young lost her abilities to hear and speak after contracting bacterial meningitis at age 13. The disease caused permanent hearing loss and temporary vocal cord paresis which became permanent after unsuccessful surgery. Hoping to advance her writing career following her publication of the novel Midnight Mass and receiving international critical acclaim, Maddie leaves New York City and lives an isolated life in the woods with her black cat. Her friend Sarah visits her one evening to return a copy of her book, and they talk about her isolation and Sarah's desire to learn more sign language.. Later that night, a masked killer with a crossbow attacks Sarah and chases her to Maddie's house. A bloodied Sarah bangs on the door shouting for help; she goes unnoticed by Maddie and the killer stabs Sarah 13 times resulting in her death.
                    """);
            final Movie movie14 = new Movie("The Godfather", LocalDateTime.now().minusDays(3), user4, """
                    In 1945 New York City, Corleone crime family don Vito Corleone listens to requests during his daughter Connie's wedding to Carlo. Michael, Vito's youngest son and a former Marine, introduces his girlfriend, Kay Adams, to his family at the reception. Johnny Fontane, a popular singer and Vito's godson, seeks Vito's help in securing a movie role. Vito sends his consigliere, Tom Hagen, to persuade studio head Jack Woltz to offer Johnny the part. Woltz refuses Hagen's request at first, but soon complies after finding the severed head of his prized racing horse in his bed.
                    """);
            final Movie movie15 = new Movie("Hacksaw Ridge", LocalDateTime.now().minusDays(5), user3, """
                    In 1925 Lynchburg, Virginia, young Desmond Doss nearly kills his brother during roughhousing. That event and his Seventh-day Adventist upbringing reinforce Desmond's belief in the commandment "Thou shalt not kill." Fifteen years later, Doss takes an injured man to the hospital and meets a nurse, Dorothy Schutte. They strike a romance, and Doss tells Dorothy of his interest in medical work.
                    After the Japanese attack on Pearl Harbor brings the United States into World War II, Doss enlists in the United States Army to serve as a combat medic. His father, Tom, a First World War veteran, is deeply upset by the decision. Desmond and Dorothy get engaged.
                    """);

            movieRepository.saveAll(List.of(movie1, movie2, movie3, movie4, movie5, movie6, movie7, movie8, movie9, movie10, movie11, movie12, movie13, movie14, movie15));

            final MovieOpinion movieOpinion1 = new MovieOpinion(movie5, user1, true, false);
            final MovieOpinion movieOpinion2 = new MovieOpinion(movie1, user4, true, false);
            final MovieOpinion movieOpinion3 = new MovieOpinion(movie4, user1, false, true);
            final MovieOpinion movieOpinion4 = new MovieOpinion(movie4, user3, true, false);
            final MovieOpinion movieOpinion5 = new MovieOpinion(movie3, user1, false, true);
            final MovieOpinion movieOpinion6 = new MovieOpinion(movie2, user2, false, true);
            final MovieOpinion movieOpinion7 = new MovieOpinion(movie2, user3, true, false);
            final MovieOpinion movieOpinion8 = new MovieOpinion(movie5, user5, true, false);
            final MovieOpinion movieOpinion9 = new MovieOpinion(movie1, user5, true, false);
            final MovieOpinion movieOpinion10 = new MovieOpinion(movie4, user5, false, true);
            final MovieOpinion movieOpinion11 = new MovieOpinion(movie4, user6, true, false);
            final MovieOpinion movieOpinion12 = new MovieOpinion(movie3, user6, false, true);
            final MovieOpinion movieOpinion13 = new MovieOpinion(movie5, user8, true, false);
            final MovieOpinion movieOpinion14 = new MovieOpinion(movie1, user8, true, false);
            final MovieOpinion movieOpinion15 = new MovieOpinion(movie4, user8, false, true);
            final MovieOpinion movieOpinion16 = new MovieOpinion(movie4, user7, true, false);
            final MovieOpinion movieOpinion17 = new MovieOpinion(movie10, user1, false, true);
            final MovieOpinion movieOpinion18 = new MovieOpinion(movie2, user7, false, true);
            final MovieOpinion movieOpinion19 = new MovieOpinion(movie10, user3, true, false);
            final MovieOpinion movieOpinion20 = new MovieOpinion(movie10, user2, false, true);
            final MovieOpinion movieOpinion21 = new MovieOpinion(movie1, user2, true, false);
            final MovieOpinion movieOpinion22 = new MovieOpinion(movie15, user10, true, false);
            final MovieOpinion movieOpinion23 = new MovieOpinion(movie15, user9, true, false);
            final MovieOpinion movieOpinion24 = new MovieOpinion(movie15, user8, true, false);
            final MovieOpinion movieOpinion25 = new MovieOpinion(movie15, user7, true, false);
            final MovieOpinion movieOpinion26 = new MovieOpinion(movie15, user6, true, false);
            final MovieOpinion movieOpinion27 = new MovieOpinion(movie15, user5, false, true);
            final MovieOpinion movieOpinion28 = new MovieOpinion(movie14, user10, true, false);
            final MovieOpinion movieOpinion29 = new MovieOpinion(movie14, user9, true, false);
            final MovieOpinion movieOpinion30 = new MovieOpinion(movie14, user8, false, true);
            final MovieOpinion movieOpinion31 = new MovieOpinion(movie14, user7, false, true);
            final MovieOpinion movieOpinion32 = new MovieOpinion(movie14, user6, true, false);
            final MovieOpinion movieOpinion33 = new MovieOpinion(movie14, user5, false, true);
            final MovieOpinion movieOpinion34 = new MovieOpinion(movie13, user6, true, false);
            final MovieOpinion movieOpinion35 = new MovieOpinion(movie13, user5, false, true);

            movieOpinionRepository.saveAll(List.of(
                    movieOpinion1, movieOpinion2, movieOpinion3, movieOpinion4, movieOpinion5, movieOpinion6, movieOpinion7,
                    movieOpinion8, movieOpinion9, movieOpinion10, movieOpinion11, movieOpinion12, movieOpinion13, movieOpinion14,
                    movieOpinion15, movieOpinion16, movieOpinion17, movieOpinion18, movieOpinion19, movieOpinion20, movieOpinion21,
                    movieOpinion22, movieOpinion23, movieOpinion24, movieOpinion25, movieOpinion26, movieOpinion27, movieOpinion28,
                    movieOpinion29, movieOpinion30, movieOpinion31, movieOpinion32, movieOpinion33, movieOpinion34, movieOpinion35));
        };
    }
}
