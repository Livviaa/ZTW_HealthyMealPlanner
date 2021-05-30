
CREATE TABLE ingredient_recipes
(
	recipe_id INT CONSTRAINT nn_ingredient_recipe_recipe_id NOT NULL,
	ingredient_id INT CONSTRAINT nn_ingredient_recipe_ingredient_id NOT NULL,
	CONSTRAINT fk_ingredient_recipe_recipe FOREIGN KEY(recipe_id) REFERENCES recipe(recipe_id),
	CONSTRAINT fk_ingredient_recipe_ingredient FOREIGN KEY(ingredient_id) REFERENCES ingredient(ingredient_id)
);


CREATE TABLE recipe_meals
(
	meal_id INT CONSTRAINT nn_recipe_meal_meal_id NOT NULL,
	recipe_id INT CONSTRAINT nn_recipe_meal_recipe_id NOT NULL,
	CONSTRAINT fk_recipe_meal_meal FOREIGN KEY(meal_id) REFERENCES meal(meal_id),
	CONSTRAINT fk_recipe_meal_recipe FOREIGN KEY(recipe_id) REFERENCES recipe(recipe_id)
);