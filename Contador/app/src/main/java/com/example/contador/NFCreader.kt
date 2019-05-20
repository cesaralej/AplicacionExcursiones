package com.example.contador

import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.IsoDep
import android.util.Log
import java.io.IOException
import java.lang.ref.WeakReference


/**
 * Callback class, invoked when an NFC card is scanned while the device is running in reader mode.
 *
 * Reader mode can be invoked by calling NfcAdapter
 */
class NFCreader(accountCallback: AccountCallback) : NfcAdapter.ReaderCallback {

    // Weak reference to prevent retain loop. mAccountCallback is responsible for exiting
    // foreground mode before it becomes invalid (e.g. during onPause() or onStop()).
    private val mAccountCallback: WeakReference<AccountCallback>
    private var nfcAdapter: NfcAdapter? = null

    interface AccountCallback {
        fun onAccountReceived(account: String)
    }

    init {
        mAccountCallback = WeakReference(accountCallback)

    }

    /**
     * Callback when a new tag is discovered by the system.
     *
     *
     * Communication with the card should take place here.
     *
     * @param tag Discovered tag
     */
    override fun onTagDiscovered(tag: Tag) {
        Log.i(TAG, "New tag discovered")
        // Android's Host-based Card Emulation (HCE) feature implements the ISO-DEP (ISO 14443-4)
        // protocol.
        //
        // In order to communicate with a device using HCE, the discovered tag should be processed
        // using the IsoDep class.
        val isoDep = IsoDep.get(tag)
        if (isoDep != null) {
            try {

            } catch (e: IOException) {
                Log.e(TAG, "Error communicating with card: $e")
            }

        }
    }

    companion object {
        private val TAG = "LoyaltyCardReader"
        // AID for our loyalty card service.
        private val SAMPLE_LOYALTY_CARD_AID = "F222222222"
        // ISO-DEP command HEADER for selecting an AID.
        // Format: [Class | Instruction | Parameter 1 | Parameter 2]- Edit database with "Add to List" and "Remove from List" buttons, changing column "Added" from False to True or vice-versa.
        private val SELECT_APDU_HEADER = "00A40400"
        // "OK" status word sent in response to SELECT AID command (0x9000)
        private val SELECT_OK_SW = byteArrayOf(0x90.toByte(), 0x00.toByte())

        /**
         * Build APDU for SELECT AID command. This command indicates which service a reader is
         * interested in communicating with. See ISO 7816-4.
         *
         * @param aid Application ID (AID) to select
         * @return APDU for SELECT AID command
         */

    }

}