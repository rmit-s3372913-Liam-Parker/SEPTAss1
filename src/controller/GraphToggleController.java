package controller;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphToggleController implements ActionListener {

    private JFreeChart graph;

    public GraphToggleController(JFreeChart graph)
    {
        this.graph = graph;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String event = e.getActionCommand();

        CategoryPlot plot;
        CategoryItemRenderer renderer;

        switch(event)
        {
            case "Temperature":
                plot = graph.getCategoryPlot();

                renderer = plot.getRenderer();
                renderer.setSeriesVisible(0, !renderer.isSeriesVisible(0));
                renderer.setSeriesVisibleInLegend(0, true, false);
                break;
            case "Apparent Temperature":
                plot = graph.getCategoryPlot();

                renderer = plot.getRenderer();
                renderer.setSeriesVisible(1, !renderer.isSeriesVisible(1));
                renderer.setSeriesVisibleInLegend(1, true, false);
                break;
            case "Dew Point":
                plot = graph.getCategoryPlot();

                renderer = plot.getRenderer();
                renderer.setSeriesVisible(2, !renderer.isSeriesVisible(2));
                renderer.setSeriesVisibleInLegend(2, true, false);
                break;
            case "Wind Speed Kts":
                plot = graph.getCategoryPlot();

                renderer = plot.getRenderer();
                renderer.setSeriesVisible(3, !renderer.isSeriesVisible(3));
                renderer.setSeriesVisibleInLegend(3, true, false);
                break;
            case "Wind Speed Kmh":
                plot = graph.getCategoryPlot();

                renderer = plot.getRenderer();
                renderer.setSeriesVisible(4, !renderer.isSeriesVisible(4));
                renderer.setSeriesVisibleInLegend(4, true, false);
                break;
            case "Rain MM":
                plot = graph.getCategoryPlot();

                renderer = plot.getRenderer();
                renderer.setSeriesVisible(5, !renderer.isSeriesVisible(5));
                renderer.setSeriesVisibleInLegend(5, true, false);
                break;
        }
    }
}
