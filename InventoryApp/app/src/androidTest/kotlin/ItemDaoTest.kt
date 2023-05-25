import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.inventory.data.InventoryDatabase
import com.example.inventory.data.Item
import com.example.inventory.data.ItemDao
import kotlinx.coroutines.flow.collect
import org.junit.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ItemDaoTest {
    private lateinit var itemDao: ItemDao
    private lateinit var inventoryDatabase: InventoryDatabase

    // declare some items to be added to the fake database
    private var item1 = Item(1, "bananas", 10.0, 20)
    private var item2 =  Item(2, "Apples", 10.0, 78)

    // add a utility function for adding tiems to the db
    private suspend fun addOneItemToDb() {
        itemDao.insert(item1)
    }

    private suspend fun addTwoItems() {
        itemDao.insert(item1)
        itemDao.insert(item2)
    }

    // add test for adding items to the DB
    @Test
    fun doInsert_insertsItemsToDb() = runBlocking {
        addOneItemToDb()
        val allItems = itemDao.getAllItems().first()
        assertEquals(allItems[0], item1)
    }

    // add several items and test
    @Test
    @Throws(Exception::class)
    fun daoGetAllItems_returnsAllItemsFromDB() = runBlocking {
        addTwoItems()
        val allItems = itemDao.getAllItems().first()
        assertEquals(allItems[0], Item(1, "bananas", 10.0, 20))
        assertEquals(allItems[1], Item(2, "Apples", 10.0, 78))
    }

    @Test
    @Throws(Exception::class)
    fun doUpdateItems_updateItemsInDB() = runBlocking {
        addTwoItems()
        itemDao.update(Item(1, "apples", 15.0, 12))
        itemDao.update(Item(2, "bananas", 15.0, 12))
        val allItems = itemDao.getAllItems().first()
        assertEquals(allItems[0], Item(1, "apples", 15.0, 12))
        assertEquals(allItems[1], Item(2, "bananas", 15.0, 12))
    }

    @Test
    @Throws(IOException::class)
    fun doDeleteItems_deletesAllItemsFromDB() = runBlocking {
        addTwoItems()
        itemDao.delete(item1)
        itemDao.delete(item2)
        val items = itemDao.getAllItems().first()
        assertEquals(items, emptyList<Item>())
    }

    @Test
    @Throws(IOException::class)
    fun daoGetItem_returnItemFromDb() = runBlocking {
        addOneItemToDb()
        val item = itemDao.getItem(1)
        assertEquals(item.first(), item1)
    }

    @Before // this will be run before every single test
    fun createdb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // create an in-memory db for testing purposes
        inventoryDatabase = Room.inMemoryDatabaseBuilder(context, InventoryDatabase::class.java)
            .allowMainThreadQueries() // running queries on the main thread: allowed for testing only
            .build()
        itemDao = inventoryDatabase.itemDao() // Dao: the entry point to the database
    }

    @After // run after every test
    @Throws(IOException::class)
    fun closeDb() {
        inventoryDatabase.close()
    }
}