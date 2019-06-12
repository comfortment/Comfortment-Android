package com.comfortment.presentation.ui.main.nanum.write

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.comfortment.R
import com.comfortment.domain.model.Nanum
import com.comfortment.presentation.ui.base.BaseFragment
import com.comfortment.presentation.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_nanum_write.*
import javax.inject.Inject

class NanumWriteFragment : BaseFragment(), NanumWriteContract.View {

    private val imgeUrl = StringBuilder()
    private lateinit var imagePath: String

    @Inject
    lateinit var nanumWritePresenter: NanumWritePresenter

    override val layoutId: Int
        get() = R.layout.fragment_nanum_write

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        range_picker.minValue = 1
        range_picker.maxValue = 24

        date_time_picker.maxValue = 1
        date_time_picker.displayedValues = arrayOf("시간", "일")

        date_time_picker.setOnValueChangedListener { _, _, newVal ->
            range_picker.maxValue = if (newVal == 0) 24 else 30
        }


        pay_at_switch.setOnCheckedChangeListener { _, isChecked ->
            pay_at_tv.text =
                if (isChecked) "후불"
                else "선불"
        }

        back_btn.setOnClickListener {
            findNavController().navigateUp()
        }

        edit_btn.setOnClickListener {
            val title = title_edit.text.toString()
            val price = price_edit.text.toString()
            val payAt =
                if (pay_at_switch.isChecked) "deferred"
                else "advanced"

            val type = when (category_spinner.selectedItem) {
                "묶음구매" -> "bundle"
                "공동구매" -> "joint"
                "떨이" -> "rummage_sale"
                "도우미" -> "worker"
                else -> ""
            }

            val expiry =
                if (date_time_picker.value == 0) {
                    range_picker.value
                } else {
                    range_picker.value * 24
                }

            val description = description_edit.text.toString()
            val bank = bank_edit.text.toString()
            val bankAccount = bank_account_edit.text.toString()

            val currentState = when {
                recruiting_check.isChecked -> "recruiting"
                paid_check.isChecked -> "paid"
                processing_check.isChecked -> "processing"
                done_check.isChecked -> "done"
                else -> ""
            }

            if (imgeUrl.isNotEmpty() && title.isNotEmpty() && price.isNotEmpty() &&
                description.isNotEmpty() && bank.isNotEmpty() && bankAccount.isNotEmpty()
            ) {
                nanumWritePresenter.postNanum(
                    title,
                    price,
                    payAt,
                    type,
                    expiry,
                    description,
                    bank,
                    bankAccount,
                    currentState,
                    imgeUrl.toString()
                )
            } else {
                showToast("제대로 입력해 주세요.")
            }
        }

        upload_image.setOnClickListener {
            try {
                if (ActivityCompat.checkSelfPermission(
                        context!!,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        activity!!,
                        arrayOf(
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ),
                        1
                    )
                } else {
                    val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(galleryIntent, 1)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        nanumWritePresenter.takeView(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1) {
            if (data != null) {
                Glide.with(context!!).load(data.data).into(upload_image)
                imgeUrl.delete(0, imgeUrl.length)
                imgeUrl.append(getRealPathFromURI(data.data!!))
            }
        }
    }

    override fun putNanumData(nanum: Nanum) {
        if (nanum.imagePath != null) {
            imagePath = nanum.imagePath
            Glide.with(context!!).load(nanum.imagePath).into(upload_image)
        }

        title_edit.setText(nanum.title)
        price_edit.setText(nanum.price.toString())
        pay_at_switch.isChecked = nanum.payAt == "deferred"

        category_spinner.setSelection(
            when (nanum.type) {
                "bundle" -> 0
                "joint" -> 1
                "rummage_sale" -> 2
                "worker" -> 3
                else -> -1
            }
        )

        if (nanum.expiry < 24) {
            range_picker.value = nanum.expiry
            date_time_picker.value = 1
        } else {
            range_picker.value = nanum.expiry / 24
            date_time_picker.value = 0
        }

        description_edit.setText(nanum.description)
        bank_edit.setText(nanum.bank)
        bank_account_edit.setText(nanum.bankAccount)

        recruiting_check.isClickable = true
        paid_check.isClickable = true
        processing_check.isClickable = true
        done_check.isClickable = true

        when (nanum.currentState) {
            "recruiting" -> {
                recruiting_check.isChecked = true
                paid_check.isChecked = false
                processing_check.isChecked = false
                done_check.isChecked = false
            }
            "paid" -> {
                recruiting_check.isChecked = false
                paid_check.isChecked = true
                processing_check.isChecked = false
                done_check.isChecked = false
            }
            "processing" -> {
                recruiting_check.isChecked = false
                paid_check.isChecked = false
                processing_check.isChecked = true
                done_check.isChecked = false
            }
            "done" -> {
                recruiting_check.isChecked = false
                paid_check.isChecked = false
                processing_check.isChecked = false
                done_check.isChecked = true
            }
        }
    }

    override fun goBack() {
        findNavController().navigateUp()
    }

    override fun showToast(text: String) = Toast.makeText(context, text, Toast.LENGTH_SHORT).show()

    override fun showLoading() {
        (activity as MainActivity).apply {
            loadingDialog.show(supportFragmentManager, "Loading")
        }
    }

    override fun hideLoading() = (activity as MainActivity).loadingDialog.dismiss()

    private fun getRealPathFromURI(contentURI: Uri): String? {
        val filePath: String?
        val cursor = context!!.contentResolver.query(contentURI, null, null, null, null)
        if (cursor == null) {
            filePath = contentURI.path
        } else {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            filePath = cursor.getString(idx)
            cursor.close()
        }
        return filePath
    }
}