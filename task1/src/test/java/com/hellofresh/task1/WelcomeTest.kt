package com.hellofresh.task1

import com.hellofresh.task1.data.FakeData
import com.hellofresh.task1.data.local.MenuDataSource
import com.hellofresh.task1.data.model.Menu
import com.hellofresh.task1.data.model.Recipe
import com.hellofresh.task1.data.model.Subscription
import com.hellofresh.task1.data.repository.MenuRepositoryImp
import com.hellofresh.task1.domain.repository.MenuRepository
import com.hellofresh.task1.domain.usecases.GetAvailableRecipesUseCase
import com.hellofresh.task1.domain.usecases.GetRecipesWithTagUseCase
import com.hellofresh.task1.domain.usecases.GetSelectedRecipesUseCase
import com.hellofresh.task1.domain.usecases.SelectMultipleRecipesUseCase
import com.hellofresh.task1.domain.usecases.SelectRecipeUseCase
import com.hellofresh.task1.domain.usecases.UnSelectRecipeUseCase
import com.hellofresh.task1.domain.usecases.UnSelectMultipleRecipesUseCase
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class WelcomeTest {
    private lateinit var fakeData: FakeData
    private lateinit var menuDataSource: MenuDataSource
    private lateinit var menuRepository: MenuRepository

    private lateinit var getAvailableRecipesUseCase: GetAvailableRecipesUseCase
    private lateinit var getSelectedRecipesUseCase: GetSelectedRecipesUseCase
    private lateinit var selectRecipeUseCase: SelectRecipeUseCase
    private lateinit var selectMultipleRecipes: SelectMultipleRecipesUseCase
    private lateinit var unSelectRecipeUseCase: UnSelectRecipeUseCase
    private lateinit var unSelectMultipleRecipesUseCase: UnSelectMultipleRecipesUseCase
    private lateinit var getRecipesWithTagUseCase: GetRecipesWithTagUseCase

    @Before
    fun setup() {
        fakeData = mockk<FakeData>()
        menuDataSource = MenuDataSource(fakeData)
        menuRepository = MenuRepositoryImp(menuDataSource)
        getAvailableRecipesUseCase = GetAvailableRecipesUseCase(menuRepository)
        getSelectedRecipesUseCase = GetSelectedRecipesUseCase(menuRepository)
        selectRecipeUseCase = SelectRecipeUseCase(menuRepository)
        selectMultipleRecipes = SelectMultipleRecipesUseCase(menuRepository)
        unSelectRecipeUseCase = UnSelectRecipeUseCase(menuRepository)
        unSelectMultipleRecipesUseCase = UnSelectMultipleRecipesUseCase(menuRepository)
        getRecipesWithTagUseCase = GetRecipesWithTagUseCase(menuRepository)
    }

    @Test
    fun `Given that available menu is that fake one When calling the first item in the return getAvailableRecipesUseCase Then it should return the right title`() {
        // Given
        every { fakeData.getMenu() } returns mockMenu

        // When
        val firstAvailableRecipe = getAvailableRecipesUseCase.build()[0]

        // Then
        assertTrue(firstAvailableRecipe.title == mockMenu.recipes[0].title)
    }

    @Test
    fun `Given that available recipes are fakeRecipes and the subscription is for a single person When selecting more than 3 recipes  one by one Then an error message thrown for disability to select more than 3`() {
        // Given
        every { fakeData.getMenu() } returns mockMenu

        val firstRecipeToSelect = mockMenu.recipes.first()
        val secondRecipeToSelect = mockMenu.recipes[1]
        val thirdRecipeToSelect = mockMenu.recipes[2]
        val forthRecipeToSelect = mockMenu.recipes[3]

        // When
        selectRecipeUseCase.build(firstRecipeToSelect)
        // Then
        assertTrue(getSelectedRecipesUseCase.build().size == 1)
        // When
        selectRecipeUseCase.build(secondRecipeToSelect)
        // Then
        assertTrue(getSelectedRecipesUseCase.build().size == 2)
        // When
        selectRecipeUseCase.build(thirdRecipeToSelect)
        // Then
        assertTrue(getSelectedRecipesUseCase.build().size == 3)

        // When
        val exception = Assert.assertThrows(IllegalArgumentException::class.java) {
            selectRecipeUseCase.build(forthRecipeToSelect)
        }

        // Then
        assertEquals(
            "Cannot select more than 3 recipes for individual subscription.",
            exception.message
        )
    }

    @Test
    fun `Given that available recipes are fakeRecipes and the subscription is for a family When selecting more than 3 recipes  one by one Then no error message thrown`() {
        // Given
        every { fakeData.getMenu() } returns mockMenu.copy(
            subscription = mockSubscription.copy(
                isForFamily = true
            )
        )

        val firstRecipeToSelect = mockMenu.recipes.first()
        val secondRecipeToSelect = mockMenu.recipes[1]
        val thirdRecipeToSelect = mockMenu.recipes[2]
        val forthRecipeToSelect = mockMenu.recipes[3]

        // When
        selectRecipeUseCase.build(firstRecipeToSelect)
        // Then
        assertTrue(getSelectedRecipesUseCase.build().size == 1)
        // When
        selectRecipeUseCase.build(secondRecipeToSelect)
        // Then
        assertTrue(getSelectedRecipesUseCase.build().size == 2)
        // When
        selectRecipeUseCase.build(thirdRecipeToSelect)
        // Then
        assertTrue(getSelectedRecipesUseCase.build().size == 3)

        // When
        selectRecipeUseCase.build(forthRecipeToSelect)
        // Then
        assertTrue(getSelectedRecipesUseCase.build().size == 4)
    }

    @Test
    fun `Given that menu is mockMenu and the subscription is for a family When selecting more than 5 recipes one by one Then an error message thrown for disability to select more than 5`() {
        // Given
        every { fakeData.getMenu() } returns mockMenu.copy(
            subscription = mockSubscription.copy(
                isForFamily = true
            )
        )

        val firstRecipeToSelect = mockMenu.recipes.first()
        val secondRecipeToSelect = mockMenu.recipes[1]
        val thirdRecipeToSelect = mockMenu.recipes[2]
        val forthRecipeToSelect = mockMenu.recipes[3]
        val fifthRecipeToSelect = mockMenu.recipes[4]
        val sixthRecipeToSelect = mockMenu.recipes[5]

        // When
        selectRecipeUseCase.build(firstRecipeToSelect)
        // Then
        assertTrue(getSelectedRecipesUseCase.build().size == 1)
        // When
        selectRecipeUseCase.build(secondRecipeToSelect)
        // Then
        assertTrue(getSelectedRecipesUseCase.build().size == 2)
        // When
        selectRecipeUseCase.build(thirdRecipeToSelect)
        // Then
        assertTrue(getSelectedRecipesUseCase.build().size == 3)
        // When
        selectRecipeUseCase.build(forthRecipeToSelect)
        // Then
        assertTrue(getSelectedRecipesUseCase.build().size == 4)
        // When
        selectRecipeUseCase.build(fifthRecipeToSelect)
        // Then
        assertTrue(getSelectedRecipesUseCase.build().size == 5)

        // When
        val exception = Assert.assertThrows(IllegalArgumentException::class.java) {
            selectRecipeUseCase.build(sixthRecipeToSelect)
        }

        // Then
        assertEquals(
            "Cannot select more than 5 recipes for individual subscription.",
            exception.message
        )
    }

    @Test
    fun `Given that menu is mockMenu When unselect individual recipe Then the selected recipes count will be reduced`() {
        // Given
        every { fakeData.getMenu() } returns mockMenu
        selectMultipleRecipes.build(mockMenu.recipes)

        // When
        unSelectRecipeUseCase.build(mockMenu.recipes[0])

        // Then
        assertTrue(getSelectedRecipesUseCase.build().size == mockMenu.recipes.size - 1)
    }

    @Test
    fun `Given that menu is mockMenu When selecting recipe that is not existing in the available recipes Then an error message thrown`() {
        // Given
        every { fakeData.getMenu() } returns mockMenu

        // When
        val exception = Assert.assertThrows(IllegalArgumentException::class.java) {
            selectRecipeUseCase.build(mockNotExistingRecipe)
        }

        // Then
        assertEquals(
            "Recipe not found in the available recipes.",
            exception.message
        )
    }

    @Test
    fun `Given that menu is mockMenu and the subscription is for a family When selecting more than 5 multiple recipes Then an error message thrown for disability to select more than 5`() {
        // Given
        every { fakeData.getMenu() } returns mockMenu.copy(
            subscription = mockSubscription.copy(
                isForFamily = true
            )
        )

        // When
        val exception = Assert.assertThrows(IllegalArgumentException::class.java) {
            selectMultipleRecipes.build(mockRecipes)
        }

        // Then
        assertEquals(
            "Cannot select more than 5 recipes for individual subscription.",
            exception.message
        )
    }


    @Test
    fun `Given that menu is mockMenu and the subscription is for a family When selecting less than 5 multiple recipes Then no error message thrown`() {
        // Given
        every { fakeData.getMenu() } returns mockMenu.copy(
            subscription = mockSubscription.copy(
                isForFamily = true
            )
        )
        // Prepare the list to be only 5 recipes
        mockRecipes.removeLast()

        // When
        selectMultipleRecipes.build(mockRecipes)

        // Then
        assertTrue(getSelectedRecipesUseCase.build().size == 5)
    }

    @Test
    fun `Given that menu is mockMenu When unselect multiple recipes Then the selected recipes count will be reduced`() {
        // Given
        every { fakeData.getMenu() } returns mockMenu
        selectMultipleRecipes.build(mockMenu.recipes)

        // When
        unSelectMultipleRecipesUseCase.build(listOf(mockMenu.recipes[0], mockMenu.recipes[1]))

        // Then
        assertTrue(getSelectedRecipesUseCase.build().size == mockMenu.recipes.size - 2)
    }

    @Test
    fun `Given that menu is mockMenu When calling getRecipesWithTagUseCase with unhealthy tag Then the filteredRecipes size should be four`() {
        // Given
        every { fakeData.getMenu() } returns mockMenu

        // When
        val filteredRecipes = getRecipesWithTagUseCase.build("unhealthy")

        // Then
        assertTrue(filteredRecipes.size == 4)
    }

    @Test
    fun `Given that menu is mockMenu When selecting multiple recipes that have non existing recipes in the available recipes Then an error message thrown`() {
        // Given
        every { fakeData.getMenu() } returns mockMenu

        // When
        val exception = Assert.assertThrows(IllegalArgumentException::class.java) {
            selectMultipleRecipes.build(listOf(mockNotExistingRecipe))
        }

        // Then
        assertEquals(
            "Some of the selected recipes not found in the available recipes.",
            exception.message
        )
    }

    companion object {
        val mockRecipes = arrayListOf(
            Recipe(0, "Spaghetti", listOf("hot", "quick")),
            Recipe(1, "Pizza", listOf("high-calories", "unhealthy")),
            Recipe(2, "Chocolate", listOf("high-calories", "unhealthy", "quick")),
            Recipe(3, "Fries", listOf("high-calories", "unhealthy")),
            Recipe(4, "Waffle", listOf("high-calories", "unhealthy")),
            Recipe(5, "Rice", listOf("low-calories", "healthy")),
        )

        val mockSubscription = Subscription(0, "Wednesday", false)

        val mockMenu = Menu(mockRecipes, mockSubscription)

        val mockNotExistingRecipe =
            Recipe(6, "Meat", listOf("low-calories", "healthy"))
    }
}