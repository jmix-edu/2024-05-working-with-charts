package com.company.jmixpmflowbase.view.requestshistory;


import com.company.jmixpmflowbase.entity.RequestHistory;
import com.company.jmixpmflowbase.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.chartsflowui.component.Chart;
import io.jmix.chartsflowui.data.ContainerChartItems;
import io.jmix.chartsflowui.data.item.EntityDataItem;
import io.jmix.chartsflowui.kit.component.model.DataSet;
import io.jmix.core.Metadata;
import io.jmix.flowui.facet.Timer;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@Route(value = "requests-history-view", layout = MainView.class)
@ViewController("RequestsHistoryView")
@ViewDescriptor("requests-history-view.xml")
public class RequestsHistoryView extends StandardView {

    @ViewComponent
    private CollectionContainer<RequestHistory> requestHistoriesDc;
    @ViewComponent
    private Chart requestsChart;
    @Autowired
    private Metadata metadata;

    private final Random random = new Random();

    @Subscribe
    public void onInit(final InitEvent event) {
        requestsChart.setDataSet(
                new DataSet().withSource(
                        new DataSet.Source<EntityDataItem>()
                                .withDataProvider(new ContainerChartItems<>(requestHistoriesDc))
                                .withCategoryField("timestamp")
                                .withValueField("requestsNumber")
                )
        );
        addData();
    }

    @Subscribe("updateChartTimer")
    public void onUpdateChartTimerTimerAction(final Timer.TimerActionEvent event) {
        addData();

    }

    private void addData() {
        RequestHistory requestHistory = metadata.create(RequestHistory.class);
        requestHistory.setTimestamp(LocalDateTime.now());
        requestHistory.setRequestsNumber(random.nextInt(100, 2000));

        requestHistoriesDc.getMutableItems().add(requestHistory);

        if (requestHistoriesDc.getItems().size() > 10) {
            requestHistoriesDc.getMutableItems().remove(0);
        }
    }


}