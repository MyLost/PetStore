package petStore;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.*;

public class PetStoreTests {

    private static final String SPECIES = "Dog";
    private static final int WEIGHT = 30;

    private static final double PRICE = 100.00;
    private PetStore petStore;
    private Animal animal;

    @Before
    public void setUp() {
        this.petStore = new PetStore();
        this.animal = new Animal(SPECIES, WEIGHT, PRICE);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_GetAnimasl_ShouldReturn_UnmodifiableList() {
        List<Animal> animals = petStore.getAnimals();
        animals.remove(1);
    }

    @Test
    public void test_getCount_ShouldReturnZeroWhenEmptyAndOneWhenSingleAnimalAdded() {
        assertEquals(0, petStore.getCount());
        petStore.addAnimal(this.animal);
        assertEquals(1, petStore.getCount());
    }


    @Test
    public void test_findAllAnimalsWithMaxKilograms() {
        petStore.addAnimal(animal);
        petStore.addAnimal(new Animal("test", WEIGHT -2 , PRICE));
        List<Animal> animals = petStore.findAllAnimalsWithMaxKilograms(WEIGHT -1);
        assertEquals(1, animals.size());
        assertEquals(animal.getSpecie(), animals.get(0).getSpecie());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddAnimal_ShouldThrow_When_Animal_Is_Null() {
        petStore.addAnimal(null);
    }

    @Test
    public void test_AddAnimal_ShouldIncreaseCount() {
        petStore.addAnimal(animal);
        assertEquals(1, petStore.getCount());
    }

    @Test
    public void test_GetTheMostExpensiveAnimal_Should_Return_Null_WhenEmpty() {
        Animal animal = petStore.getTheMostExpensiveAnimal();
        assertNull(animal);
    }


    @Test
    public void test_GetTheMostExpensiveAnimal_Should_Return_TheMostExpensiveOne() {
        petStore.addAnimal(animal);
        petStore.addAnimal(new Animal(SPECIES, WEIGHT, PRICE - 10));
        Animal actualAnimal = petStore.getTheMostExpensiveAnimal();
        assertEquals(animal.getPrice(), actualAnimal.getPrice(), 0.00);
    }

    @Test
    public void test_FindAllAnimalsBySpecie_ShouldReturn_Empty_When_NoAnimals() {
        List<Animal> animals = petStore.findAllAnimalBySpecie(SPECIES);
        assertTrue(animals.isEmpty());
    }


    @Test
    public void test_FindAllAnimalsBySpecie_ShouldReturn_OnlyRequiredSpecies() {
        petStore.addAnimal(animal);
        petStore.addAnimal(new Animal("Goat", WEIGHT, PRICE));
        List<Animal> animals = petStore.findAllAnimalBySpecie(SPECIES);
        assertEquals(1, animals.size());
        assertEquals(SPECIES, animals.get(0).getSpecie());
    }

}

