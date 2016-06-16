clear all;
close all;
%% Plot for Lidingo
clear all;
close all;
M = csvread('Gamla.csv',1,0);
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

%% Correlation study
clear all;
close all;

data = csvread('Satra.csv',1,0);
M=data(~any(isnan(data),2),:);
M(:,1:2)=M(:,1:2)./10^6;

R = corrcoef(M);

%% Linear regression
clear all;
close all;
order=4;
testseqsize=10;
vect_input=[1,3,6];
vect_plot=7;
vect_output=2;
data = csvread('Satra.csv',1,0);
M=data(~any(isnan(data),2),:);
M(:,1:2)=M(:,1:2)./10^6;
eta=10^-7;
nbiter=1000;

[ W,phi ] = LinearRegression( M(testseqsize+1:end,vect_input),M(testseqsize+1:end,vect_output),order );
%[ W,phi ] = LinearRegressionSteepestDescent(  M(testseqsize+1:end,vect_input),M(testseqsize+1:end,vect_output),order,eta,nbiter );
MSE_training=mean((M(testseqsize+1:end,vect_output)-phi*W).^2);
error_training=M(testseqsize+1:end,vect_output)-phi*W;
mean_abs_error_training=mean(abs(error_training));

figure;
histogram(error_training,100);

[ ~,phi_test] = LinearRegression( M(1:testseqsize,vect_input),M(1:testseqsize,vect_output),order);
MSE_test=mean((M(1:testseqsize,vect_output)-phi_test*W).^2);
error_test=M(1:testseqsize,vect_output)-phi_test*W;
figure;
histogram(error_test,10);

figure
    hold on
    plot(M(testseqsize+1:end,vect_plot),M(testseqsize+1:end,vect_output),'o');
    plot(M(testseqsize+1:end,vect_plot),phi*W,'x');
    
    
