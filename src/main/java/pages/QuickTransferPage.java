package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

public class QuickTransferPage extends BasePage {

    @Getter
    private final Locator transferReceivers;
    @Getter
    private final Locator amountInput;
    @Getter
    private final Locator titleInput;
    @Getter
    private final Locator executeButton;
    @Getter
    private final Locator dialogConfirmation;
    public QuickTransferPage(Page page) {
        super(page);
        this.transferReceivers = page.locator("#widget_1_transfer_receiver");
        this.amountInput = page.locator("#widget_1_transfer_amount");
        this.titleInput = page.locator("#widget_1_transfer_title");
        this.executeButton = page.locator("#execute_btn");
        this.dialogConfirmation = page.locator(".ui-dialog .ui-dialog-content");
    }

    public void transferFunds(String receiver, String amount, String title) {
        transferReceivers.selectOption(receiver);
        amountInput.fill(amount);
        titleInput.fill(title);
        executeButton.click();
    }

    public String getDialogConfirmationText() {
        return dialogConfirmation.innerText().replace("\n", " ");
    }
}
