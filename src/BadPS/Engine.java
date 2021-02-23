package BadPS;

public class Engine {
    Layer[] layers = new Layer[0];

    public void addLayer(Layer l){
        if(layers.length == 0){
            layers = new Layer[]{l};
        }else{
            Layer[] updated = new Layer[layers.length+1];
            for(int i  = 0; i < layers.length; i++){
                updated[i] = layers[i];
            }
            updated[updated.length - 1] = l;
            layers = updated;
        }
    }

    public Layer[] getSortedLayers(){
        sort(layers);
        return layers;
    }

    private static void sort(Layer[] arr){
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j].depth > arr[j+1].depth)
                {
                    Layer temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
    }
}
