clear all;
close all;
%% Plot for Lidingo
clear all;
close all;
M = csvread('Lidingo.csv',1,0);
figure
subplot(311)
hold on
ylim([0 10000000])
xlim([0 200])
s=size(M,1);

for k=1:s
    if (M(k,3)~=0)
        plot(M(k,3),M(k,1),'o');      
        %drawnow;
    end
end

subplot(312)

hold on
ylim([0 10000000])
xlim([0 200])
s=size(M,1);

for k=1:s
    if (M(k,3)~=0)
        plot(M(k,3),M(k,2),'o');      
        %drawnow;
    end
end

subplot(313)

hold on
xlim([0 200])
s=size(M,1);

for k=1:s
    if (M(k,3)~=0)
        plot(M(k,3),M(k,2)-M(k,1),'o');      
        %drawnow;
    end
end
%% Plot for Gamla/Lidingo/Satra
clear all;
close all
plotArea('Gamla');


