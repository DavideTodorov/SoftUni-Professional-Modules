package bankSafe;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.LinkedHashMap;
import java.util.Map;

public class BankVaultTest {
    private BankVault bankVault;

    @Before
    public void setUp() {
        this.bankVault = new BankVault();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetVaultCellsAsUnmodifiable() {
        Map<String, Item> vaultCells = bankVault.getVaultCells();
        vaultCells.remove("A1");
    }

    @Test
    public void testGetVaultCells() {
        Map<String, Item> vaultCells = bankVault.getVaultCells();
        Map<String, Item> expected = new LinkedHashMap<>();
        expected.put("A1", null);
        expected.put("A2", null);
        expected.put("A3", null);
        expected.put("A4", null);
        expected.put("B1", null);
        expected.put("B2", null);
        expected.put("B3", null);
        expected.put("B4", null);
        expected.put("C1", null);
        expected.put("C2", null);
        expected.put("C3", null);
        expected.put("C4", null);
        Assert.assertEquals(expected, vaultCells);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetUnmodifiable() {
        bankVault.getVaultCells().remove("A1");
    }

    @Test
    public void testAddValid() throws OperationNotSupportedException {
        Item item = new Item("owner", "123");
        String a1 = bankVault.addItem("A1", item);
        Assert.assertEquals("Item:123 saved successfully!", a1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullToNotExistingCell() throws OperationNotSupportedException {
        bankVault.addItem("A6", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItemToNotExistingCell() throws OperationNotSupportedException {
        Item item = new Item("owner", "123");
        bankVault.addItem("A6", item);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testAddSameToTakenCell() throws OperationNotSupportedException {
        Item item = new Item("owner", "123");
        bankVault.addItem("A3", item);
        bankVault.addItem("A3", item);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddDiffToTakenCell() throws OperationNotSupportedException {
        Item item = new Item("owner", "123");
        Item item2 = new Item("owner2", "1234");
        bankVault.addItem("A3", item);
        bankVault.addItem("A3", item2);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddExistingElementCell() throws OperationNotSupportedException {
        Item item = new Item("owner", "123");
        bankVault.addItem("A3", item);
        bankVault.addItem("A2", item);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveFromNotExistingCell() {
        Item item = new Item("owner", "123");
        bankVault.removeItem("A8", item);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNotPresentItem() throws OperationNotSupportedException {
        Item item = new Item("owner", "123");
        Item item2 = new Item("owner2", "1234");
        bankVault.addItem("A1", item);
        bankVault.removeItem("A1", item2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveFromNotExistingNoneExisting() {
        Item item = new Item("owner", "123");
        bankVault.removeItem("A8", item);
    }

    @Test
    public void testRemoveValidElement() throws OperationNotSupportedException {
        Item item = new Item("owner", "123");
        bankVault.addItem("A1", item);
        String a1 = bankVault.removeItem("A1", item);
        Assert.assertEquals("Remove item:123 successfully!", a1);
    }

    @Test
    public void testRemoveAndValidateNull() throws OperationNotSupportedException {
        Item item = new Item("owner", "123");
        bankVault.addItem("A1",item);
        bankVault.removeItem("A1",item);
        Item item1 = bankVault.getVaultCells().get("A1");
        Assert.assertNull(item1);
    }
}