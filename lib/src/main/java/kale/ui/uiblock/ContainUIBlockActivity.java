package kale.ui.uiblock;

import android.content.Intent;

/**
 * @author Jack Tony
 * @date 2015/10/8
 */
public interface ContainUIBlockActivity {

    UIBlockManager getUiBlockManager();

    void onBackPressed();

    void onDestroy();
    
    void onActivityResult(int requestCode, int resultCode, Intent data);
}
