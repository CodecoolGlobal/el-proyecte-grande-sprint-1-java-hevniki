-- Inserting ingredient types
INSERT INTO ingredient_type (id, name, category, approved)
VALUES
    (1, 'Flour', 'WHEAT', true),
    (2, 'Milk', 'MILK_OR_DAIRY', true),
    (3, 'Eggs', 'EGG', true),
    (4, 'Chicken', 'MEAT', true),
    (5, 'Butter', 'MILK_OR_DAIRY', true);

-- Inserting recipes
INSERT INTO recipe (id, name, description, created_by, dairy_free, gluten_free, vegan, vegetarian, contains_tree_nuts)
VALUES
    (1, 'Vegan Pizza', 'Delicious vegan pizza recipe', 'admin', true, true, true, true, false),
    (2, 'Gluten-Free Pancakes', 'Yummy gluten-free pancake recipe', 'admin', true, true, false, true, false),
    (3, 'Vegetarian Lasagna', 'Classic vegetarian lasagna recipe', 'admin',  true, true, false, true, false),
    (4, 'Chicken Alfredo Pasta', 'Creamy chicken alfredo pasta recipe', 'admin',  false, true, false, false, false),
    (5, 'Chocolate Cake', 'Decadent chocolate cake recipe', 'admin',  true, true, true, true, false);

-- Inserting ingredients for recipes
INSERT INTO ingredient_for_recipe (id, amount, ingredient_type_id, recipe_id)
VALUES
    (1, '300 g', 1, 1),  -- Flour for Vegan Pizza
    (2, '500 g', 1, 2),  -- Flour for Gluten-Free Pancakes
    (3, '300 g', 2, 2),  -- Milk for Gluten-Free Pancakes
    (4, '2', 3, 2),    -- Egg for Gluten-Free Pancakes
    (5, '40 dkg', 1, 3),  -- Flour for Vegetarian Lasagna
    (6, '200 g', 5, 3),  -- Butter for Vegetarian Lasagna
    (7, '0.5 kg', 1, 4),  -- Flour for Chicken Alfredo Pasta
    (8, '0.5 kg', 4, 4),  -- Chicken for Chicken Alfredo Pasta
    (9, '30 dl', 2, 5),  -- Milk for Chocolate Cake
    (10, '500 g', 1, 5), -- Flour for Chocolate Cake
    (11, '15 dkg', 5, 5); -- Butter for Chocolate Cake

