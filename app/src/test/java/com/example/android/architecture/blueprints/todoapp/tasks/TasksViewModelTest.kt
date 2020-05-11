package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

// DONE Step 6.5 Add the AndoirdJUnit4 test runner:
@RunWith(AndroidJUnit4::class)
class TasksViewModelTest {

    // DONE Step 10.0: Create a lateinit instance variable called tasksViewModel
    private lateinit var taskViewModel: TasksViewModel

    // DONE Step 10.1 Create a method called setupViewModel and annotate it with @Before
    @Before
    fun setUpViewModel() {
        // DONE Step 10.2 Move the view model instantiation code to setupViewModel
        taskViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())
    }

    // DONE Step 8.1 create a variable from InstantTaskExecutorRule
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // DONE Step 8.2 make a new Kotlin file called LiveDataTestUtil.kt in your test source set
    // DONE Step 8.3 Copy and paste the provided code
    //  NOTE for full explanatioon follow the link https://medium.com/androiddevelopers/unit-testing-livedata-and-other-common-observability-problems-bb477262eb04

    // DONE Step 6.1: Create a new test called addNewTask_setsNewTaskEvent:
    @Test
    fun addNewTask_setsNewTaskEvent() {
        // Given a fresh TasksViewModel
        // DONE Step 6.3 Create a TasksViewModel using ApplicationProvider.getApplicationContext() from the AndroidX test library
//        val taskViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())


        // When adding a new task
        // DONE Step 6.4 add a new task
        taskViewModel.addNewTask()

        // Then the new task event is triggered
        // DONE Step 8.4 Get the LiveData value for newTaskEvent using getOrAwaitValue
        val value = taskViewModel.newTaskEvent.getOrAwaitValue()

        // DONE Step 8.5 Assert that the value is not null:
        assertThat(value.getContentIfNotHandled(), (not(nullValue())))

    }

    // DONE Step 9: Write your own ViewModel test
    //  write a test called setFilterAllTasks_tasksAddViewVisible() that:
    //  - Sets the filtering mode to TasksFilterType.ALL_TASKS
    //  - Assets that the tasksAddViewVisible LiveData is true
    @Test
    fun setFilterAllTasks_tasksAddViewVisible() {
        // Given a fresh ViewModel
//        val tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())

        // When the filter type is ALL_TASKS
        taskViewModel.setFiltering(TasksFilterType.ALL_TASKS)

        // Then the "Add task" action is visible
        val tasksAddViewVisible = taskViewModel.tasksAddViewVisible.getOrAwaitValue()
        assertThat(tasksAddViewVisible, `is`(true))

    }
}