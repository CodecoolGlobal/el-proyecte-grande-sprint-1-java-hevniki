-- Inserting ingredient types
INSERT INTO ingredient_type (id, is_dairy_free, is_egg_free, is_gluten_free, is_meat_free, name, unit_of_measure)
VALUES
    (1, true, true, true, false, 'Flour', 'grams'),
    (2, true, false, true, true, 'Milk', 'milliliters'),
    (3, true, true, true, false, 'Egg', 'units'),
    (4, false, false, true, false, 'Chicken', 'grams'),
    (5, true, false, true, false, 'Butter', 'grams');

-- Inserting recipes
INSERT INTO recipe (id, name, description, dairy_free, gluten_free, vegan, vegetarian)
VALUES
    (1, 'Vegan Pizza', 'Delicious vegan pizza recipe', true, true, true, true),
    (2, 'Gluten-Free Pancakes', 'Yummy gluten-free pancake recipe', true, true, false, true),
    (3, 'Vegetarian Lasagna', 'Classic vegetarian lasagna recipe', true, true, false, true),
    (4, 'Chicken Alfredo Pasta', 'Creamy chicken alfredo pasta recipe', false, true, false, false),
    (5, 'Chocolate Cake', 'Decadent chocolate cake recipe', true, true, true, true);

-- Inserting ingredients for recipes
INSERT INTO ingredient_for_recipe (id, amount, ingredient_type_id, recipe_id)
VALUES
    (1, 300.0, 1, 1),  -- Flour for Vegan Pizza
    (2, 500.0, 1, 2),  -- Flour for Gluten-Free Pancakes
    (3, 300.0, 2, 2),  -- Milk for Gluten-Free Pancakes
    (4, 2.0, 3, 2),    -- Egg for Gluten-Free Pancakes
    (5, 400.0, 1, 3),  -- Flour for Vegetarian Lasagna
    (6, 200.0, 5, 3),  -- Butter for Vegetarian Lasagna
    (7, 500.0, 1, 4),  -- Flour for Chicken Alfredo Pasta
    (8, 400.0, 4, 4),  -- Chicken for Chicken Alfredo Pasta
    (9, 300.0, 2, 5),  -- Milk for Chocolate Cake
    (10, 200.0, 1, 5), -- Flour for Chocolate Cake
    (11, 150.0, 5, 5); -- Butter for Chocolate Cake
