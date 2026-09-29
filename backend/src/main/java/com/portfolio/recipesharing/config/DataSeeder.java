package com.portfolio.recipesharing.config;

import com.portfolio.recipesharing.entity.Recipe;
import com.portfolio.recipesharing.entity.Review;
import com.portfolio.recipesharing.entity.User;
import com.portfolio.recipesharing.enums.CuisineType;
import com.portfolio.recipesharing.repository.RecipeRepository;
import com.portfolio.recipesharing.repository.ReviewRepository;
import com.portfolio.recipesharing.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;
    private final ReviewRepository reviewRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository, RecipeRepository recipeRepository,
                      ReviewRepository reviewRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
        this.reviewRepository = reviewRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() > 0) return;

        User admin = new User();
        admin.setUsername("admin");
        admin.setEmail("admin@recipesharing.com");
        admin.setPassword(passwordEncoder.encode("admin123"));
        userRepository.save(admin);

        User chef = new User();
        chef.setUsername("chef_maria");
        chef.setEmail("maria@recipesharing.com");
        chef.setPassword(passwordEncoder.encode("maria123"));
        userRepository.save(chef);

        // ITALIAN
        Recipe r1 = saveRecipe(admin, CuisineType.ITALIAN, "VEGETARIAN",
                "Classic Margherita Pizza", "Pizza Margherita Clássica",
                "A traditional Neapolitan pizza with fresh basil, mozzarella, and tomato sauce on a thin crispy crust.",
                "Uma pizza napolitana tradicional com manjericão fresco, mussarela e molho de tomate em uma massa fina e crocante.",
                "500g bread flour\n7g active dry yeast\n1 tsp salt\n1 tsp sugar\n325ml warm water\n2 tbsp olive oil\n200g San Marzano tomatoes\n250g fresh mozzarella\nFresh basil leaves",
                "500g farinha de pão\n7g fermento seco ativo\n1 colher de chá de sal\n1 colher de chá de açúcar\n325ml água morna\n2 colheres de sopa de azeite\n200g tomates San Marzano\n250g mussarela fresca\nFolhas de manjericão fresco",
                "1. Mix flour, yeast, salt, sugar. Add water and olive oil, knead 10 minutes.\n2. Let dough rise 1 hour.\n3. Preheat oven to 250°C with pizza stone.\n4. Crush tomatoes with salt for sauce.\n5. Stretch dough thin, add sauce, torn mozzarella.\n6. Bake 10-12 minutes until bubbly.\n7. Top with fresh basil and drizzle of olive oil.",
                "1. Misture farinha, fermento, sal, açúcar. Adicione água e azeite, sove por 10 minutos.\n2. Deixe a massa crescer por 1 hora.\n3. Pré-aqueça o forno a 250°C com pedra de pizza.\n4. Amasse os tomates com sal para o molho.\n5. Estique a massa fina, adicione molho e mussarela rasgada.\n6. Asse 10-12 minutos até borbulhar.\n7. Finalize com manjericão fresco e fio de azeite.",
                20, 12, 4);

        // BRAZILIAN
        Recipe r2 = saveRecipe(chef, CuisineType.BRAZILIAN, "GLUTEN_FREE",
                "Traditional Feijoada", "Feijoada Tradicional",
                "Brazil's national dish — a rich black bean stew with pork, served with rice, collard greens, and orange slices.",
                "O prato nacional do Brasil — um rico ensopado de feijão preto com carne de porco, servido com arroz, couve e laranja.",
                "500g black beans\n300g pork ribs\n200g smoked sausage\n150g dried beef (carne seca)\n1 large onion\n6 cloves garlic\n3 bay leaves\nSalt and pepper to taste\nOrange slices for serving",
                "500g feijão preto\n300g costela de porco\n200g linguiça defumada\n150g carne seca\n1 cebola grande\n6 dentes de alho\n3 folhas de louro\nSal e pimenta a gosto\nFatias de laranja para servir",
                "1. Soak beans overnight, drain.\n2. Cut meats into chunks, soak dried beef separately.\n3. In a large pot, sauté onion and garlic.\n4. Add beans, meats, bay leaves, and water to cover.\n5. Simmer on low heat for 3-4 hours.\n6. Serve with white rice, sautéed collard greens, and orange slices.",
                "1. Deixe o feijão de molho durante a noite, escorra.\n2. Corte as carnes em pedaços, deixe a carne seca de molho separadamente.\n3. Em uma panela grande, refogue cebola e alho.\n4. Adicione feijão, carnes, louro e água para cobrir.\n5. Cozinhe em fogo baixo por 3-4 horas.\n6. Sirva com arroz branco, couve refogada e fatias de laranja.",
                30, 240, 8);

        // MEXICAN
        Recipe r3 = saveRecipe(admin, CuisineType.MEXICAN, "NONE",
                "Chicken Tacos al Pastor", "Tacos de Frango al Pastor",
                "Marinated chicken with pineapple, cilantro, and onion in warm corn tortillas.",
                "Frango marinado com abacaxi, coentro e cebola em tortilhas de milho quentes.",
                "600g chicken thighs\n3 dried guajillo chiles\n2 chipotle peppers in adobo\n1/2 pineapple, sliced\n1 white onion\nFresh cilantro\n12 corn tortillas\nLimes",
                "600g coxas de frango\n3 pimentas guajillo secas\n2 pimentas chipotle em adobo\n1/2 abacaxi fatiado\n1 cebola branca\nCoentro fresco\n12 tortilhas de milho\nLimões",
                "1. Rehydrate guajillo chiles in hot water 15 min.\n2. Blend chiles, chipotle, garlic, cumin, oregano into paste.\n3. Marinate chicken in paste 2+ hours.\n4. Grill chicken and pineapple slices.\n5. Dice chicken, chop pineapple.\n6. Warm tortillas, fill with chicken, pineapple, onion, cilantro.\n7. Squeeze lime over and serve.",
                "1. Reidrate as pimentas guajillo em água quente por 15 min.\n2. Bata as pimentas, chipotle, alho, cominho, orégano em uma pasta.\n3. Marine o frango na pasta por 2+ horas.\n4. Grelhe o frango e as fatias de abacaxi.\n5. Pique o frango e o abacaxi.\n6. Aqueça as tortilhas, recheie com frango, abacaxi, cebola, coentro.\n7. Esprema limão por cima e sirva.",
                30, 20, 4);

        // ASIAN
        Recipe r4 = saveRecipe(chef, CuisineType.ASIAN, "DAIRY_FREE",
                "Vegetable Pad Thai", "Pad Thai de Vegetais",
                "Classic Thai stir-fried rice noodles with vegetables, tofu, peanuts, and tamarind sauce.",
                "Clássico macarrão de arroz tailandês salteado com vegetais, tofu, amendoim e molho de tamarindo.",
                "250g rice noodles\n200g firm tofu\n2 eggs\n1 cup bean sprouts\n3 green onions\n1/3 cup roasted peanuts\n3 tbsp tamarind paste\n2 tbsp fish sauce\n1 tbsp sugar\nLime wedges",
                "250g macarrão de arroz\n200g tofu firme\n2 ovos\n1 xícara de broto de feijão\n3 cebolinhas\n1/3 xícara de amendoim torrado\n3 colheres de sopa de pasta de tamarindo\n2 colheres de sopa de molho de peixe\n1 colher de sopa de açúcar\nGominhos de limão",
                "1. Soak rice noodles in warm water 30 min, drain.\n2. Mix tamarind paste, fish sauce, sugar for sauce.\n3. Press and cube tofu, pan-fry until golden.\n4. Scramble eggs in wok, set aside.\n5. Stir-fry noodles with sauce 2-3 min.\n6. Add tofu, eggs, bean sprouts, green onions.\n7. Top with crushed peanuts and lime.",
                "1. Deixe o macarrão de molho em água morna por 30 min, escorra.\n2. Misture pasta de tamarindo, molho de peixe, açúcar para o molho.\n3. Pressione e corte o tofu em cubos, frite até dourar.\n4. Mexa os ovos na wok, reserve.\n5. Salteie o macarrão com o molho por 2-3 min.\n6. Adicione tofu, ovos, broto de feijão, cebolinha.\n7. Finalize com amendoim triturado e limão.",
                15, 15, 3);

        // MEDITERRANEAN
        Recipe r5 = saveRecipe(admin, CuisineType.MEDITERRANEAN, "VEGETARIAN,GLUTEN_FREE",
                "Greek Salad with Feta", "Salada Grega com Feta",
                "A refreshing Mediterranean salad with ripe tomatoes, cucumbers, olives, and feta cheese.",
                "Uma salada mediterrânea refrescante com tomates maduros, pepinos, azeitonas e queijo feta.",
                "4 ripe tomatoes\n1 cucumber\n1 red onion\n200g feta cheese\n100g Kalamata olives\n1 green bell pepper\n4 tbsp extra virgin olive oil\n2 tbsp red wine vinegar\n1 tsp dried oregano",
                "4 tomates maduros\n1 pepino\n1 cebola roxa\n200g queijo feta\n100g azeitonas Kalamata\n1 pimentão verde\n4 colheres de sopa de azeite extra virgem\n2 colheres de sopa de vinagre de vinho tinto\n1 colher de chá de orégano seco",
                "1. Cut tomatoes into wedges, slice cucumber.\n2. Thinly slice red onion and bell pepper.\n3. Combine vegetables in a large bowl.\n4. Add olives.\n5. Whisk olive oil, vinegar, oregano, salt, pepper.\n6. Pour dressing over salad.\n7. Place feta block on top, sprinkle with oregano.",
                "1. Corte os tomates em gomos, fatie o pepino.\n2. Fatie finamente a cebola roxa e o pimentão.\n3. Combine os vegetais em uma tigela grande.\n4. Adicione as azeitonas.\n5. Bata azeite, vinagre, orégano, sal, pimenta.\n6. Despeje o tempero sobre a salada.\n7. Coloque o bloco de feta por cima, polvilhe com orégano.",
                15, 0, 4);

        // INDIAN
        Recipe r6 = saveRecipe(chef, CuisineType.INDIAN, "VEGETARIAN,VEGAN",
                "Chickpea Tikka Masala", "Tikka Masala de Grão-de-Bico",
                "A creamy, spiced tomato curry with chickpeas, served over fragrant basmati rice.",
                "Um curry cremoso e condimentado de tomate com grão-de-bico, servido sobre arroz basmati aromático.",
                "2 cans chickpeas\n1 can coconut milk\n400g crushed tomatoes\n1 large onion\n4 cloves garlic\n1 inch ginger\n2 tbsp garam masala\n1 tsp turmeric\n1 tsp cumin\nFresh cilantro",
                "2 latas de grão-de-bico\n1 lata de leite de coco\n400g tomates triturados\n1 cebola grande\n4 dentes de alho\n1 pedaço de gengibre\n2 colheres de sopa de garam masala\n1 colher de chá de açafrão\n1 colher de chá de cominho\nCoentro fresco",
                "1. Sauté diced onion until soft.\n2. Add minced garlic and ginger, cook 1 min.\n3. Add garam masala, turmeric, cumin, stir 30 sec.\n4. Pour in crushed tomatoes, simmer 10 min.\n5. Add coconut milk and drained chickpeas.\n6. Simmer 15-20 min until thick.\n7. Serve over basmati rice with fresh cilantro.",
                "1. Refogue a cebola picada até ficar macia.\n2. Adicione alho e gengibre picados, cozinhe 1 min.\n3. Adicione garam masala, açafrão, cominho, mexa 30 seg.\n4. Despeje os tomates triturados, cozinhe 10 min.\n5. Adicione leite de coco e grão-de-bico escorrido.\n6. Cozinhe 15-20 min até engrossar.\n7. Sirva sobre arroz basmati com coentro fresco.",
                15, 30, 4);

        // FRENCH
        Recipe r7 = saveRecipe(admin, CuisineType.FRENCH, "NONE",
                "Classic French Onion Soup", "Sopa de Cebola Francesa Clássica",
                "Rich caramelized onion soup topped with crusty bread and melted Gruyère cheese.",
                "Rica sopa de cebola caramelizada coberta com pão crocante e queijo Gruyère derretido.",
                "6 large onions\n4 tbsp butter\n1 cup dry white wine\n6 cups beef broth\n1 baguette, sliced\n200g Gruyère cheese, grated\n2 sprigs fresh thyme\n1 bay leaf\nSalt and pepper",
                "6 cebolas grandes\n4 colheres de sopa de manteiga\n1 xícara de vinho branco seco\n6 xícaras de caldo de carne\n1 baguete fatiada\n200g queijo Gruyère ralado\n2 ramos de tomilho fresco\n1 folha de louro\nSal e pimenta",
                "1. Slice onions thinly.\n2. Melt butter in heavy pot, cook onions 45 min on medium-low, stirring often.\n3. Deglaze with white wine, reduce by half.\n4. Add broth, thyme, bay leaf. Simmer 20 min.\n5. Ladle into oven-safe bowls.\n6. Top with baguette slices, pile on Gruyère.\n7. Broil until cheese is bubbly and golden.",
                "1. Fatie as cebolas finamente.\n2. Derreta a manteiga em panela pesada, cozinhe as cebolas por 45 min em fogo médio-baixo, mexendo frequentemente.\n3. Deglace com vinho branco, reduza pela metade.\n4. Adicione caldo, tomilho, louro. Cozinhe 20 min.\n5. Coloque em tigelas refratárias.\n6. Cubra com fatias de baguete e queijo Gruyère.\n7. Gratine até o queijo borbulhar e dourar.",
                15, 75, 6);

        // AMERICAN
        Recipe r8 = saveRecipe(chef, CuisineType.AMERICAN, "HIGH_PROTEIN",
                "Smoked BBQ Pulled Pork", "Pulled Pork Defumado com Molho BBQ",
                "Slow-cooked pork shoulder with homemade BBQ sauce, perfect for sandwiches.",
                "Paleta de porco cozida lentamente com molho BBQ caseiro, perfeita para sanduíches.",
                "2kg pork shoulder\n2 tbsp paprika\n1 tbsp garlic powder\n1 tbsp onion powder\n1 tbsp brown sugar\n1 cup BBQ sauce\n1/2 cup apple cider vinegar\n1 cup chicken broth\nBrioche buns\nColeslaw",
                "2kg paleta de porco\n2 colheres de sopa de páprica\n1 colher de sopa de alho em pó\n1 colher de sopa de cebola em pó\n1 colher de sopa de açúcar mascavo\n1 xícara de molho BBQ\n1/2 xícara de vinagre de maçã\n1 xícara de caldo de frango\nPães brioche\nSalada coleslaw",
                "1. Mix paprika, garlic powder, onion powder, brown sugar, salt, pepper.\n2. Rub spice mix all over pork shoulder.\n3. Place in slow cooker with broth and vinegar.\n4. Cook on low 8-10 hours until fall-apart tender.\n5. Shred pork with two forks.\n6. Mix with BBQ sauce.\n7. Serve on brioche buns with coleslaw.",
                "1. Misture páprica, alho em pó, cebola em pó, açúcar mascavo, sal, pimenta.\n2. Esfregue a mistura em toda a paleta.\n3. Coloque na panela elétrica com caldo e vinagre.\n4. Cozinhe em fogo baixo por 8-10 horas até desmanchar.\n5. Desfie a carne com dois garfos.\n6. Misture com molho BBQ.\n7. Sirva em pães brioche com coleslaw.",
                20, 480, 10);

        // ASIAN - Sushi
        Recipe r9 = saveRecipe(admin, CuisineType.ASIAN, "DAIRY_FREE",
                "Salmon Nigiri Sushi", "Sushi Nigiri de Salmão",
                "Simple and elegant Japanese rice balls topped with fresh salmon slices.",
                "Simples e elegantes bolinhos de arroz japoneses cobertos com fatias de salmão fresco.",
                "300g sushi-grade salmon\n2 cups sushi rice\n3 tbsp rice vinegar\n1 tbsp sugar\n1 tsp salt\nWasabi\nSoy sauce\nPickled ginger",
                "300g salmão para sushi\n2 xícaras de arroz para sushi\n3 colheres de sopa de vinagre de arroz\n1 colher de sopa de açúcar\n1 colher de chá de sal\nWasabi\nMolho de soja\nGengibre em conserva",
                "1. Cook sushi rice, let cool slightly.\n2. Mix rice vinegar, sugar, salt. Fold into rice.\n3. Slice salmon into thin pieces at an angle.\n4. Wet hands, form small oblong rice balls.\n5. Dab a tiny bit of wasabi on rice.\n6. Drape salmon slice over rice ball, press gently.\n7. Serve with soy sauce and pickled ginger.",
                "1. Cozinhe o arroz para sushi, deixe esfriar um pouco.\n2. Misture vinagre de arroz, açúcar, sal. Incorpore ao arroz.\n3. Fatie o salmão em pedaços finos em ângulo.\n4. Molhe as mãos, forme pequenos bolinhos ovais de arroz.\n5. Coloque um pouquinho de wasabi sobre o arroz.\n6. Coloque a fatia de salmão sobre o bolinho, pressione gentilmente.\n7. Sirva com molho de soja e gengibre em conserva.",
                30, 20, 2);

        // BRAZILIAN - Pão de Queijo
        Recipe r10 = saveRecipe(chef, CuisineType.BRAZILIAN, "GLUTEN_FREE",
                "Brazilian Cheese Bread", "Pão de Queijo Mineiro",
                "Crispy on the outside, soft and chewy inside — Brazil's beloved cheese bread from Minas Gerais.",
                "Crocante por fora, macio e elástico por dentro — o amado pão de queijo mineiro.",
                "500g tapioca flour\n1 cup whole milk\n1/2 cup vegetable oil\n2 eggs\n200g Parmesan cheese, grated\n100g mozzarella, grated\n1 tsp salt",
                "500g polvilho azedo\n1 xícara de leite integral\n1/2 xícara de óleo vegetal\n2 ovos\n200g queijo parmesão ralado\n100g mussarela ralada\n1 colher de chá de sal",
                "1. Preheat oven to 180°C.\n2. Heat milk, oil, and salt until boiling.\n3. Pour over tapioca flour, mix well.\n4. Let cool until warm to touch.\n5. Add eggs one at a time, mixing well.\n6. Fold in both cheeses.\n7. Roll into small balls, bake 20-25 min until golden.",
                "1. Pré-aqueça o forno a 180°C.\n2. Aqueça leite, óleo e sal até ferver.\n3. Despeje sobre o polvilho, misture bem.\n4. Deixe esfriar até ficar morno.\n5. Adicione os ovos um de cada vez, misturando bem.\n6. Incorpore os dois queijos.\n7. Enrole em bolinhas, asse 20-25 min até dourar.",
                15, 25, 20);

        // MEDITERRANEAN - Hummus
        Recipe r11 = saveRecipe(admin, CuisineType.MEDITERRANEAN, "VEGAN,GLUTEN_FREE",
                "Classic Hummus", "Homus Clássico",
                "Creamy Lebanese hummus made with chickpeas, tahini, lemon, and garlic.",
                "Homus libanês cremoso feito com grão-de-bico, tahini, limão e alho.",
                "2 cans chickpeas\n1/4 cup tahini\n3 tbsp lemon juice\n2 cloves garlic\n3 tbsp olive oil\n1/2 tsp cumin\nSalt to taste\nPaprika for garnish",
                "2 latas de grão-de-bico\n1/4 xícara de tahini\n3 colheres de sopa de suco de limão\n2 dentes de alho\n3 colheres de sopa de azeite\n1/2 colher de chá de cominho\nSal a gosto\nPáprica para decorar",
                "1. Drain chickpeas, reserve liquid.\n2. Blend chickpeas, tahini, lemon juice, garlic in food processor.\n3. Add olive oil and cumin.\n4. Blend until very smooth, adding reserved liquid as needed.\n5. Season with salt.\n6. Serve drizzled with olive oil and a dusting of paprika.",
                "1. Escorra o grão-de-bico, reserve o líquido.\n2. Bata grão-de-bico, tahini, suco de limão, alho no processador.\n3. Adicione azeite e cominho.\n4. Bata até ficar bem cremoso, adicionando líquido reservado conforme necessário.\n5. Tempere com sal.\n6. Sirva regado com azeite e polvilhado com páprica.",
                10, 0, 6);

        // ITALIAN - Tiramisu
        Recipe r12 = saveRecipe(chef, CuisineType.ITALIAN, "VEGETARIAN",
                "Classic Tiramisu", "Tiramisù Clássico",
                "The beloved Italian dessert with layers of coffee-soaked ladyfingers and mascarpone cream.",
                "A amada sobremesa italiana com camadas de biscoitos embebidos em café e creme de mascarpone.",
                "6 egg yolks\n3/4 cup sugar\n500g mascarpone\n2 cups strong espresso, cooled\n3 tbsp coffee liqueur\n300g ladyfinger biscuits\nCocoa powder",
                "6 gemas de ovo\n3/4 xícara de açúcar\n500g mascarpone\n2 xícaras de espresso forte, resfriado\n3 colheres de sopa de licor de café\n300g biscoitos champagne\nCacau em pó",
                "1. Whisk egg yolks and sugar until thick and pale.\n2. Add mascarpone, fold gently until smooth.\n3. Mix espresso with coffee liqueur.\n4. Briefly dip ladyfingers in coffee mixture.\n5. Layer soaked biscuits in a dish.\n6. Spread half the mascarpone cream, repeat layers.\n7. Refrigerate 6+ hours. Dust with cocoa before serving.",
                "1. Bata gemas e açúcar até ficar espesso e claro.\n2. Adicione mascarpone, misture delicadamente até ficar liso.\n3. Misture espresso com licor de café.\n4. Mergulhe brevemente os biscoitos na mistura de café.\n5. Faça uma camada de biscoitos em uma travessa.\n6. Espalhe metade do creme, repita as camadas.\n7. Refrigere por 6+ horas. Polvilhe com cacau antes de servir.",
                30, 0, 8);

        // Add some reviews
        addReview(admin, r1, 5, "Perfect pizza recipe!", "Receita de pizza perfeita!");
        addReview(chef, r1, 4, "Great crust, would add more basil.", "Ótima massa, adicionaria mais manjericão.");
        addReview(admin, r2, 5, "Authentic feijoada taste!", "Sabor autêntico de feijoada!");
        addReview(chef, r4, 5, "Restaurant quality pad thai.", "Pad Thai com qualidade de restaurante.");
        addReview(admin, r6, 4, "Love this vegan option.", "Adoro essa opção vegana.");
        addReview(chef, r10, 5, "Best pão de queijo recipe ever!", "Melhor receita de pão de queijo!");
        addReview(admin, r12, 5, "Heavenly dessert!", "Sobremesa divina!");
        addReview(chef, r7, 4, "Rich and comforting soup.", "Sopa rica e reconfortante.");
    }

    private Recipe saveRecipe(User author, CuisineType cuisine, String dietary,
                               String title, String titlePt, String desc, String descPt,
                               String ingredients, String ingredientsPt,
                               String instructions, String instructionsPt,
                               int prep, int cook, int servings) {
        Recipe r = new Recipe();
        r.setTitle(title);
        r.setTitlePt(titlePt);
        r.setDescription(desc);
        r.setDescriptionPt(descPt);
        r.setIngredients(ingredients);
        r.setIngredientsPt(ingredientsPt);
        r.setInstructions(instructions);
        r.setInstructionsPt(instructionsPt);
        r.setPrepTimeMinutes(prep);
        r.setCookTimeMinutes(cook);
        r.setServings(servings);
        r.setCuisine(cuisine);
        r.setDietaryTags(dietary);
        r.setAuthor(author);
        r.setAverageRating(0.0);
        return recipeRepository.save(r);
    }

    private void addReview(User author, Recipe recipe, int rating, String comment, String commentPt) {
        Review review = new Review();
        review.setRating(rating);
        review.setComment(comment);
        review.setCommentPt(commentPt);
        review.setRecipe(recipe);
        review.setAuthor(author);
        reviewRepository.save(review);

        Double avg = reviewRepository.getAverageRatingByRecipeId(recipe.getId());
        recipe.setAverageRating(avg != null ? Math.round(avg * 10.0) / 10.0 : 0.0);
        recipeRepository.save(recipe);
    }
}
