package com.ryfa.MVP;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.ryfa.MVP.databinding.ActivityLineChartBinding;
import com.ryfa.MVP.fragments.dialog.LoadingDialogFragment;
import com.ryfa.MVP.general.PersianCalendar;
import com.ryfa.MVP.general.SharedPrefHandler;
import com.ryfa.MVP.interfaces.OnObjectResultCallBak;
import com.ryfa.MVP.models.PayModel;
import com.ryfa.MVP.models.UserModel;
import com.ryfa.MVP.widgets.SnackBar;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.ChartUtils;

public class ChartMyInfoPaysActivity extends AppCompatActivity {

    Context context = this;
    Activity activity = this;
    ActivityLineChartBinding binding;
    UserModel.User user;
    ArrayList<Float> list = new ArrayList<>();
    private LineChartData data;
    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasLines = true;
    private boolean hasPoints = true;
    private ValueShape shape = ValueShape.CIRCLE;
    private boolean isFilled = false;
    private boolean hasLabels = true;
    private boolean isCubic = false;
    private boolean hasLabelForSelected = false;
    private boolean pointsHaveDifferentColor;
    private boolean hasGradientToTransparent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLineChartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        user = new Gson().fromJson(SharedPrefHandler.getStringPreference(context, "USER", ""), UserModel.User.class);

        binding.chart.setOnValueTouchListener(new ValueTouchListener());

        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        for (int i = 0; i < 7; i++) {
            list.add(0f);
        }

        PayModel.getAllPaysApp(user.getId(), new OnObjectResultCallBak<PayModel>() {
            @Override
            public void onStart(LoadingDialogFragment loadingDialog) {
                loadingDialog.show(getSupportFragmentManager(), LoadingDialogFragment.TAG);
            }

            @Override
            public void onResponse(PayModel object, LoadingDialogFragment loadingDialog) {
                loadingDialog.dismiss();
                if (object.getStatus() == 200) {
                    if (object.getPays().size() < 1) {

                    } else {
                        for (int i = 0; i < object.getPays().size(); i++) {
                            PayModel.Pay pay = object.getPays().get(i);
                            int day = PersianCalendar.getInstance(pay.getDate()).getPersianNumWeekDayStr();
                            list.set(day, list.get(day) + 1f);
                        }

                    generateData();
                }
            }
        }

        @Override
        public void onFailure ( int statusCode, LoadingDialogFragment loadingDialog){
            loadingDialog.dismiss();
        }
    });


}


    private void generateData() {

        List<Line> lines = new ArrayList<Line>();

        List<PointValue> values = new ArrayList<PointValue>();

        values.add(new PointValue(2f, list.get(0)).setLabel("شنبه"));
        values.add(new PointValue(3f, list.get(1)).setLabel("یکشنبه"));
        values.add(new PointValue(4f, list.get(2)).setLabel("دوشنبه"));
        values.add(new PointValue(5f, list.get(3)).setLabel("سه شنبه"));
        values.add(new PointValue(6f, list.get(4)).setLabel("چهارشنبه"));
        values.add(new PointValue(7f, list.get(5)).setLabel("پنجشنبه"));
        values.add(new PointValue(8f, list.get(6)).setLabel("جمعه"));


        Line line = new Line(values);
        line.setColor(ChartUtils.COLORS[1]);
        line.setShape(shape);
        line.setCubic(isCubic);
        line.setFilled(isFilled);

        line.setHasLabels(hasLabels);
        line.setHasLabelsOnlyForSelected(hasLabelForSelected);
        line.setHasLines(hasLines);
        line.setHasPoints(hasPoints);
//            line.setHasGradientToTransparent(hasGradientToTransparent);
        if (pointsHaveDifferentColor) {
            line.setPointColor(ChartUtils.COLORS[(1 + 1) % ChartUtils.COLORS.length]);
        }
        lines.add(line);


        data = new LineChartData(lines);

        if (hasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setName("Axis X");
                axisY.setName("Axis Y");
            }
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        data.setBaseValue(Float.NEGATIVE_INFINITY);
        binding.chart.setLineChartData(data);

    }

private class ValueTouchListener implements LineChartOnValueSelectListener {

    @Override
    public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
        char[] lable = value.getLabel();
        float count = value.getY();
        String str = new String(lable);

        SnackBar.make(context, binding.layout, "تعداد خرید شما در روزهای " + str + " " + count + " می باشد", "باشه").showIndefinite(new SnackBar.SetAction() {
            @Override
            public void setConfigAction() {

            }

            @Override
            public void setCancelAction() {

            }
        });

    }

    @Override
    public void onValueDeselected() {
        // TODO Auto-generated method stub

    }

}
}